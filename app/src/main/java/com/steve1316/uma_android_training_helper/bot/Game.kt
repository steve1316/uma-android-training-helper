package com.steve1316.uma_android_training_helper.bot

import android.content.Context
import android.util.Log
import com.steve1316.uma_android_training_helper.data.CharacterData
import com.steve1316.uma_android_training_helper.data.SupportData
import com.steve1316.uma_android_training_helper.ui.settings.SettingsFragment
import com.steve1316.uma_android_training_helper.utils.ImageUtils
import com.steve1316.uma_android_training_helper.utils.MessageLog
import com.steve1316.uma_android_training_helper.utils.NotificationUtils
import net.ricecode.similarity.JaroWinklerStrategy
import net.ricecode.similarity.StringSimilarityServiceImpl
import java.util.concurrent.TimeUnit

/**
 * Main driver for bot activity and navigation.
 */
class Game(private val myContext: Context) {
	private val TAG: String = "UATH_Game"
	
	private var debugMode: Boolean = SettingsFragment.getBooleanSharedPreference(myContext, "debugMode")
	
	private val imageUtils: ImageUtils = ImageUtils(myContext, this)
	
	private val startTime: Long = System.currentTimeMillis()
	
	/**
	 * Returns a formatted string of the elapsed time since the bot started as HH:MM:SS format.
	 *
	 * Source is from https://stackoverflow.com/questions/9027317/how-to-convert-milliseconds-to-hhmmss-format/9027379
	 *
	 * @return String of HH:MM:SS format of the elapsed time.
	 */
	private fun printTime(): String {
		val elapsedMillis: Long = System.currentTimeMillis() - startTime
		
		return String.format(
			"%02d:%02d:%02d",
			TimeUnit.MILLISECONDS.toHours(elapsedMillis),
			TimeUnit.MILLISECONDS.toMinutes(elapsedMillis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(elapsedMillis)),
			TimeUnit.MILLISECONDS.toSeconds(elapsedMillis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(elapsedMillis))
		)
	}
	
	/**
	 * Print the specified message to debug console and then saves the message to the log.
	 *
	 * @param message Message to be saved.
	 * @param MESSAGE_TAG TAG to distinguish between messages for where they came from. Defaults to Game's TAG.
	 * @param isError Flag to determine whether to display log message in console as debug or error.
	 */
	fun printToLog(message: String, MESSAGE_TAG: String = TAG, isError: Boolean = false) {
		if (!isError) {
			Log.d(MESSAGE_TAG, message)
		} else {
			Log.e(MESSAGE_TAG, message)
		}
		
		// Remove the newline prefix if needed and place it where it should be.
		if (message.startsWith("\n")) {
			val newMessage = message.removePrefix("\n")
			MessageLog.messageLog.add("\n" + printTime() + " " + newMessage)
		} else {
			MessageLog.messageLog.add(printTime() + " " + message)
		}
	}
	
	/**
	 * Bot will begin automation here.
	 *
	 * @return True if all automation goals have been met. False otherwise.
	 */
	fun start(): Boolean {
		val character = SettingsFragment.getStringSharedPreference(myContext, "character")
		val rSupportCards: List<String> = SettingsFragment.getStringSharedPreference(myContext, "supportRList").split("|")
		val srSupportCards: List<String> = SettingsFragment.getStringSharedPreference(myContext, "supportSRList").split("|")
		val ssrSupportCards: List<String> = SettingsFragment.getStringSharedPreference(myContext, "supportSSRList").split("|")
		
		// Perform Tesseract OCR detection.
		var result = imageUtils.findText()
		if (result.isEmpty() || result == "empty!") {
			return false
		}
		
		// Make some minor improvements by replacing certain incorrect characters with their Japanese equivalents.
		if (result.last() == '/') {
			result = result.replace("/", "！")
		}
		result = result.replace("(", "（")
		result = result.replace(")", "）")
		
		var confidence = 0.0
		var newIndex = ""
		var type = ""
		
		// Use the Jaro Winkler algorithm to compare similarities the ocr detected string and the rest of the strings.
		val service = StringSimilarityServiceImpl(JaroWinklerStrategy())
		
		// Attempt to find the most similar string to the resulting string from OCR detection starting with the Character-specific events.
		CharacterData.characters[character]?.forEach { (eventName, _) ->
			val score = service.score(result, eventName)
			printToLog("[CHARA] $character \"${result}\" vs. \"${eventName}\" confidence: $score")
			if (score > confidence) {
				confidence = score
				newIndex = eventName
				type = "character"
			}
		}
		
		// Now move on to the Character-shared events.
		CharacterData.characters["Shared"]?.forEach { (eventName, _) ->
			val score = service.score(result, eventName)
			printToLog("[CHARA-SHARED] \"${result}\" vs. \"${eventName}\" confidence: $score")
			if (score > confidence) {
				confidence = score
				newIndex = eventName
				type = "character-shared"
			}
		}
		
		// Finally, do the same with the user-selected Support Cards.
		rSupportCards.forEach { supportCardName ->
			SupportData.R[supportCardName]?.forEach { (eventName, _) ->
				val score = service.score(result, eventName)
				printToLog("[R-SUPPORT] $supportCardName \"${result}\" vs. \"${eventName}\" confidence: $score")
				if (score > confidence) {
					confidence = score
					newIndex = eventName
					type = "support-r"
				}
			}
		}
		
		srSupportCards.forEach { supportCardName ->
			SupportData.SR[supportCardName]?.forEach { (eventName, _) ->
				val score = service.score(result, eventName)
				printToLog("[SR-SUPPORT] $supportCardName \"${result}\" vs. \"${eventName}\" confidence: $score")
				if (score > confidence) {
					confidence = score
					newIndex = eventName
					type = "support-sr"
				}
			}
		}
		
		ssrSupportCards.forEach { supportCardName ->
			SupportData.SSR[supportCardName]?.forEach { (eventName, _) ->
				val score = service.score(result, eventName)
				printToLog("[SSR-SUPPORT] $supportCardName \"${result}\" vs. \"${eventName}\" confidence: $score")
				if (score > confidence) {
					confidence = score
					newIndex = eventName
					type = "support-ssr"
				}
			}
		}
		
		printToLog("[RESULT] confidence = $confidence, newIndex = $newIndex, type = $type")
		
		// Now prepare to send the Event Title, its option rewards, and the confidence to the application's Notification.
		var optionNumber = 1
		var eventTitle = ""
		var resultString = ""
		if (confidence > 0.6) {
			when (type) {
				"character" -> {
					CharacterData.characters[character]!!.entries.forEach { (eventName, eventOptions) ->
						if (eventName == newIndex) {
							eventOptions.forEach { option ->
								printToLog("[OPTION $optionNumber] \n$option\n")
								resultString += "[OPTION $optionNumber] \n$option\n"
								optionNumber += 1
							}
							
							eventTitle = eventName
						}
					}
				}
				"character-shared" -> {
					CharacterData.characters["Shared"]!!.entries.forEach { (eventName, eventOptions) ->
						if (eventName == newIndex) {
							eventOptions.forEach { option ->
								printToLog("[OPTION $optionNumber] \n$option\n")
								resultString += "[OPTION $optionNumber] \n$option\n"
								optionNumber += 1
							}
							
							eventTitle = eventName
						}
					}
				}
				"support-r" -> {
					rSupportCards.forEach { supportCardName ->
						SupportData.R[supportCardName]?.forEach { (eventName, eventOptions) ->
							if (eventName == newIndex) {
								eventOptions.forEach { option ->
									printToLog("[OPTION $optionNumber] \n$option\n")
									resultString += "[OPTION $optionNumber] \n$option\n"
									optionNumber += 1
								}
								
								eventTitle = eventName
							}
						}
					}
				}
				"support-sr" -> {
					srSupportCards.forEach { supportCardName ->
						SupportData.SR[supportCardName]?.forEach { (eventName, eventOptions) ->
							if (eventName == newIndex) {
								eventOptions.forEach { option ->
									printToLog("[OPTION $optionNumber] \n$option\n")
									resultString += "[OPTION $optionNumber] \n$option\n"
									optionNumber += 1
								}
								
								eventTitle = eventName
							}
						}
					}
				}
				"support-ssr" -> {
					ssrSupportCards.forEach { supportCardName ->
						SupportData.SSR[supportCardName]?.forEach { (eventName, eventOptions) ->
							if (eventName == newIndex) {
								eventOptions.forEach { option ->
									printToLog("[OPTION $optionNumber] \n$option\n")
									resultString += "[OPTION $optionNumber] \n$option\n"
									optionNumber += 1
								}
								
								eventTitle = eventName
							}
						}
					}
				}
			}
			
			// Display the information to the user as a newly updated Notification.
			NotificationUtils.updateNotification(myContext, eventTitle, resultString, confidence)
		} else {
			NotificationUtils.updateNotification(
				myContext, "OCR Failed", "Sorry, either Tesseract failed to detect text or the detected text is below the required " +
						"confidence minimum.", confidence)
		}
		
		return true
	}
}