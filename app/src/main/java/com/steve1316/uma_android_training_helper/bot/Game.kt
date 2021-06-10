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
	 * Bot will begin automation here.
	 *
	 * @return True if all automation goals have been met. False otherwise.
	 */
	fun start(): Boolean {
		var confidence = 0.0
		var category = ""
		var eventTitle = ""
		var supportCardTitle = ""
		var eventOptionRewards: ArrayList<String> = arrayListOf()
		var eventOptionNumber = 1
		var notificationTextBody = ""
		
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
		
		// Use the Jaro Winkler algorithm to compare similarities the ocr detected string and the rest of the strings.
		val service = StringSimilarityServiceImpl(JaroWinklerStrategy())
		
		// Attempt to find the most similar string to the resulting string from OCR detection starting with the Character-specific events.
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
		rSupportCards.forEach { supportCardName ->
			SupportData.R[supportCardName]?.forEach { (eventName, eventOptions) ->
				val score = service.score(result, eventName)
				if (!hideResults) {
					printToLog("[R-SUPPORT] $supportCardName \"${result}\" vs. \"${eventName}\" confidence: $score")
				}
				
				if (score > confidence) {
					confidence = score
					eventTitle = eventName
					supportCardTitle = supportCardName
					eventOptionRewards = eventOptions
					category = "support-r"
				}
			}
		}
		
		srSupportCards.forEach { supportCardName ->
			SupportData.SR[supportCardName]?.forEach { (eventName, eventOptions) ->
				val score = service.score(result, eventName)
				if (!hideResults) {
					printToLog("[SR-SUPPORT] $supportCardName \"${result}\" vs. \"${eventName}\" confidence: $score")
				}
				
				if (score > confidence) {
					confidence = score
					eventTitle = eventName
					supportCardTitle = supportCardName
					eventOptionRewards = eventOptions
					category = "support-sr"
				}
			}
		}
		
		ssrSupportCards.forEach { supportCardName ->
			SupportData.SSR[supportCardName]?.forEach { (eventName, eventOptions) ->
				val score = service.score(result, eventName)
				if (!hideResults) {
					printToLog("[SSR-SUPPORT] $supportCardName \"${result}\" vs. \"${eventName}\" confidence: $score")
				}
				
				if (score > confidence) {
					confidence = score
					eventTitle = eventName
					supportCardTitle = supportCardName
					eventOptionRewards = eventOptions
					category = "support-ssr"
				}
			}
		}
		
		when (category) {
			"character" -> {
				printToLog("\n[RESULT] Character $character Event Name = $eventTitle with confidence = $confidence")
			}
			"character-shared" -> {
				printToLog("\n[RESULT] Character $character Shared Event Name = $eventTitle with confidence = $confidence")
			}
			"support-r" -> {
				printToLog("\n[RESULT] R Support $supportCardTitle Event Name = $eventTitle with confidence = $confidence")
			}
			"support-sr" -> {
				printToLog("\n[RESULT] SR Support $supportCardTitle Event Name = $eventTitle with confidence = $confidence")
			}
			"support-ssr" -> {
				printToLog("\n[RESULT] SSR Support $supportCardTitle Event Name = $eventTitle with confidence = $confidence")
			}
		}
		
		// Now construct the text body for the Notification.
		if (confidence > 0.6) {
			// Now process the resulting string from the acquired information.
			eventOptionRewards.forEach { reward ->
				if (eventOptionRewards.size == 1) {
					if (notificationTextBody.lines().count() <= 8) {
						val lineList = reward.split("\n")
						lineList.forEach { line ->
							if (notificationTextBody.lines().count() <= 8) {
								notificationTextBody += "$line\n"
							}
						}
					}
					
					printToLog("\n$reward\n")
					eventOptionNumber += 1
				} else {
					if (notificationTextBody.lines().count() <= 8) {
						val lineList = reward.split("\n")
						notificationTextBody += "[OPTION $eventOptionNumber] \n"
						lineList.forEach { line ->
							if (notificationTextBody.lines().count() <= 8) {
								notificationTextBody += "$line\n"
							}
						}
					}
					
					printToLog("[OPTION $eventOptionNumber] \n$reward\n")
					eventOptionNumber += 1
				}
			}
			
			// Append this last line to the Notification's text body if the rest of the text was going to be cut off as the Notification's expanded mode is set only at 256dp.
			if (notificationTextBody.lines().count() >= 9) {
				notificationTextBody += "Text is cut off. Please Tap me to see the full text!"
			}
			
			// Display the information to the user as a newly updated Notification.
			NotificationUtils.updateNotification(myContext, eventTitle, notificationTextBody, confidence)
		} else {
			printToLog("\n[ERROR] Confidence of $confidence failed to be greater than the minimum of 0.60 so OCR failed.")
			
			NotificationUtils.updateNotification(
				myContext, "OCR Failed", "Sorry, either Tesseract failed to detect text or the detected text is below the required confidence minimum.", confidence)
		}
		
		return true
	}
}