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
	
	private var hideResults: Boolean = SettingsFragment.getBooleanSharedPreference(myContext, "hideResults")
	private var selectAllSupportCards: Boolean = SettingsFragment.getBooleanSharedPreference(myContext, "selectAllSupportCards")
	
	private val imageUtils: ImageUtils = ImageUtils(myContext, this)
	
	private val startTime: Long = System.currentTimeMillis()
	
	private var result = ""
	private var confidence = 0.0
	private var category = ""
	private var eventTitle = ""
	private var supportCardTitle = ""
	private var eventOptionRewards: ArrayList<String> = arrayListOf()
	private var eventOptionNumber = 1
	private var firstLine = true
	private var notificationTextBody: String = ""
	private val notificationTextArray = arrayListOf<String>()
	
	private val character = SettingsFragment.getStringSharedPreference(myContext, "character")
	private val supportCards: List<String> = SettingsFragment.getStringSharedPreference(myContext, "supportList").split("|")
	
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
	 * @param isOption Flag to determine whether to append a newline right after the time in the string.
	 */
	fun printToLog(message: String, MESSAGE_TAG: String = TAG, isError: Boolean = false, isOption: Boolean = false) {
		if (!isError) {
			Log.d(MESSAGE_TAG, message)
		} else {
			Log.e(MESSAGE_TAG, message)
		}
		
		// Remove the newline prefix if needed and place it where it should be.
		if (message.startsWith("\n")) {
			val newMessage = message.removePrefix("\n")
			if (isOption) {
				MessageLog.messageLog.add("\n" + printTime() + "\n" + newMessage)
			} else {
				MessageLog.messageLog.add("\n" + printTime() + " " + newMessage)
			}
		} else {
			if (isOption) {
				MessageLog.messageLog.add(printTime() + "\n" + message)
			} else {
				MessageLog.messageLog.add(printTime() + " " + message)
			}
		}
	}
	
	/**
	 * Fix incorrect characters determined by OCR by replacing them with their Japanese equivalents.
	 */
	private fun fixIncorrectCharacters() {
		printToLog("\n[INFO] Now attempting to fix incorrect characters in: $result")
		
		if (result.last() == '/') {
			result = result.replace("/", "！")
		}
		
		result = result.replace("(", "（").replace(")", "）")
		printToLog("[INFO] Finished attempting to fix incorrect characters: $result")
	}
	
	/**
	 * Attempt to find the most similar string from data compared to the string returned by OCR.
	 */
	private fun findMostSimilarString() {
		printToLog("\n[INFO] Now starting process to find most similar string to: $result\n")
		
		// Use the Jaro Winkler algorithm to compare similarities the OCR detected string and the rest of the strings inside the data classes.
		val service = StringSimilarityServiceImpl(JaroWinklerStrategy())
		
		// Attempt to find the most similar string inside the data classes starting with the Character-specific events.
		CharacterData.characters[character]?.forEach { (eventName, eventOptions) ->
			val score = service.score(result, eventName)
			if (!hideResults) {
				printToLog("[CHARA] $character \"${result}\" vs. \"${eventName}\" confidence: $score")
			}
			
			if (score > confidence) {
				confidence = score
				eventTitle = eventName
				eventOptionRewards = eventOptions
				category = "character"
			}
		}
		
		// Now move on to the Character-shared events.
		CharacterData.characters["Shared"]?.forEach { (eventName, eventOptions) ->
			val score = service.score(result, eventName)
			if (!hideResults) {
				printToLog("[CHARA-SHARED] \"${result}\" vs. \"${eventName}\" confidence: $score")
			}
			
			if (score > confidence) {
				confidence = score
				eventTitle = eventName
				eventOptionRewards = eventOptions
				category = "character-shared"
			}
		}
		
		// Finally, do the same with the user-selected Support Cards.
		if (!selectAllSupportCards) {
			supportCards.forEach { supportCardName ->
				SupportData.supports[supportCardName]?.forEach { (eventName, eventOptions) ->
					val score = service.score(result, eventName)
					if (!hideResults) {
						printToLog("[SUPPORT] $supportCardName \"${result}\" vs. \"${eventName}\" confidence: $score")
					}
					
					if (score > confidence) {
						confidence = score
						eventTitle = eventName
						supportCardTitle = supportCardName
						eventOptionRewards = eventOptions
						category = "support"
					}
				}
			}
		} else {
			SupportData.supports.forEach { (supportName, support) ->
				support.forEach { (eventName, eventOptions) ->
					val score = service.score(result, eventName)
					if (!hideResults) {
						printToLog("[SUPPORT] $supportName \"${result}\" vs. \"${eventName}\" confidence: $score")
					}
					
					if (score > confidence) {
						confidence = score
						eventTitle = eventName
						supportCardTitle = supportName
						eventOptionRewards = eventOptions
						category = "support"
					}
				}
			}
		}
		
		printToLog("\n[INFO] Finished process to find similar string.")
	}
	
	/**
	 * Construct the result's text body and then display it as a Notification.
	 */
	private fun constructNotification() {
		// Now construct the text body for the Notification.
		if (confidence > 0.6) {
			// Process the resulting string from the acquired information.
			eventOptionRewards.forEach { reward ->
				if (eventOptionRewards.size == 1) {
					if (notificationTextArray.size < 9) {
						val lineList = reward.split("\n")
						lineList.forEach { line ->
							if (notificationTextArray.size < 9) {
								if (firstLine) {
									notificationTextArray.add(line)
									firstLine = false
								} else {
									notificationTextArray.add("\n$line")
								}
							}
						}
					}
					
					printToLog("\n\n$reward\n", isOption = true)
					eventOptionNumber += 1
				} else {
					if (notificationTextArray.size < 9) {
						val lineList = reward.split("\n")
						if (!firstLine) {
							notificationTextArray.add("\n[OPTION $eventOptionNumber]")
						} else {
							notificationTextArray.add("[OPTION $eventOptionNumber]\n")
						}
						
						lineList.forEach { line ->
							if (notificationTextArray.size < 9) {
								if (firstLine) {
									notificationTextArray.add(line)
									firstLine = false
								} else {
									notificationTextArray.add("\n$line")
								}
							}
						}
					}
					
					printToLog("\n[OPTION $eventOptionNumber] \n$reward\n", isOption = true)
					eventOptionNumber += 1
				}
			}
			
			// Append this last line to the Notification's text body if the rest of the text was going to be cut off as the Notification's expanded mode is set only at 256dp.
			if (notificationTextArray.size >= 8) {
				notificationTextArray.removeAt(notificationTextArray.lastIndex)
				notificationTextArray.add("\n*Text is cut off. Please Tap me to see the full text!*")
			}
			
			// Now join the array into its new text body string for the Notification.
			notificationTextBody = notificationTextArray.joinToString("")
			
			// Display the information to the user as a newly updated Notification.
			NotificationUtils.updateNotification(myContext, eventTitle, notificationTextBody, confidence)
		} else {
			printToLog("\n[ERROR] Confidence of $confidence failed to be greater than the minimum of 0.60 so OCR failed.")
			
			NotificationUtils.updateNotification(
				myContext, "OCR Failed", "Sorry, either Tesseract failed to detect text or the detected text is below the required confidence minimum.", confidence)
		}
	}
	
	/**
	 * Bot will begin automation here.
	 *
	 * @return True if all automation goals have been met. False otherwise.
	 */
	fun start(): Boolean {
		// Perform Tesseract OCR detection.
		result = imageUtils.findText()
		if (result.isEmpty() || result == "empty!") {
			return false
		}
		
		// Make some minor improvements by replacing certain incorrect characters with their Japanese equivalents.
		fixIncorrectCharacters()
		
		// Now attempt to find the most similar string compared to the one from OCR.
		findMostSimilarString()
		
		when (category) {
			"character" -> {
				printToLog("\n[RESULT] Character $character Event Name = $eventTitle with confidence = $confidence")
			}
			"character-shared" -> {
				printToLog("\n[RESULT] Character $character Shared Event Name = $eventTitle with confidence = $confidence")
			}
			"support" -> {
				printToLog("\n[RESULT] Support $supportCardTitle Event Name = $eventTitle with confidence = $confidence")
			}
		}
		
		// Now construct and display the Notification containing the results from OCR, whether it was successful or not.
		constructNotification()
		
		return true
	}
}