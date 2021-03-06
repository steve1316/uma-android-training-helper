package com.steve1316.uma_android_training_helper.ui.home

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.projection.MediaProjectionManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import com.beust.klaxon.JsonReader
import com.github.javiersantos.appupdater.AppUpdater
import com.github.javiersantos.appupdater.enums.UpdateFrom
import com.steve1316.uma_android_training_helper.MainActivity
import com.steve1316.uma_android_training_helper.R
import com.steve1316.uma_android_training_helper.data.CharacterData
import com.steve1316.uma_android_training_helper.data.SkillData
import com.steve1316.uma_android_training_helper.data.SupportData
import com.steve1316.uma_android_training_helper.utils.MediaProjectionService
import com.steve1316.uma_android_training_helper.utils.MessageLog
import java.io.StringReader

class HomeFragment : Fragment() {
	private val TAG: String = "[${MainActivity.loggerTag}]HomeFragment"
	private var firstBoot = false
	private var firstRun = true
	
	private lateinit var myContext: Context
	private lateinit var homeFragmentView: View
	private lateinit var startButton: Button
	
	@SuppressLint("SetTextI18n")
	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		myContext = requireContext()
		
		homeFragmentView = inflater.inflate(R.layout.fragment_home, container, false)
		
		// Start or stop the MediaProjection service via this button.
		startButton = homeFragmentView.findViewById(R.id.start_button)
		startButton.setOnClickListener {
			val readyCheck = startReadyCheck()
			if (readyCheck && !MediaProjectionService.isRunning) {
				startProjection()
				startButton.text = getString(R.string.stop)
				
				// This is needed because onResume() is immediately called right after accepting the MediaProjection and it has not been properly
				// initialized yet so it would cause the button's text to revert back to "Start".
				firstBoot = true
			} else if (MediaProjectionService.isRunning) {
				stopProjection()
				startButton.text = getString(R.string.start)
			}
		}
		
		// Update the TextView here based on the information of the SharedPreferences.
		val settingsStatusTextView: TextView = homeFragmentView.findViewById(R.id.settings_status)
		
		val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
		val character = sharedPreferences.getString("character", "")
		val supportList = sharedPreferences.getString("supportList", "")?.split("|")
		val threshold = sharedPreferences.getInt("threshold", 230)
		val hideResults = sharedPreferences.getBoolean("hideResults", true)
		val selectAllCharacters = sharedPreferences.getBoolean("selectAllCharacters", true)
		val selectAllSupportCards = sharedPreferences.getBoolean("selectAllSupportCards", true)
		val enableIncrementalThreshold = sharedPreferences.getBoolean("enableIncrementalThreshold", false)
		val confidence = sharedPreferences.getInt("confidence", 80)
		val debugMode: Boolean = sharedPreferences.getBoolean("debugMode", false)
		
		////////////////////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////////////
		// Set these values in SharedPreferences just in case these keys do not exist yet.
		
		sharedPreferences.edit {
			putBoolean("selectAllCharacters", selectAllCharacters)
			putBoolean("selectAllSupportCards", selectAllSupportCards)
			putBoolean("enableIncrementalThreshold", enableIncrementalThreshold)
			putBoolean("hideResults", hideResults)
			commit()
		}
		
		////////////////////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////////////
		// Now construct the strings to print them.
		
		val characterString: String? = if (selectAllCharacters) {
			"All Characters Selected"
		} else if (character == "" || character == "Please select one in the Settings") {
			"Please select one in the Settings"
		} else {
			character
		}
		
		val supportCardListString: String = if (selectAllSupportCards) {
			"All Support Cards Selected"
		} else {
			supportList.toString()
		}
		
		val enableAutomaticRetryString: String = if (enableIncrementalThreshold) {
			"Enabled"
		} else {
			"Disabled"
		}
		
		val hideComparisonResultsString: String = if (hideResults) {
			"Enabled"
		} else {
			"Disabled"
		}
		
		val enableDebugModeString: String = if (debugMode) {
			"Enabled"
		} else {
			"Disabled"
		}
		
		////////////////////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////////////
		// Update the TextView here based on the information of the SharedPreferences.
		
		settingsStatusTextView.setTextColor(Color.WHITE)
		settingsStatusTextView.text = "---------- Training Event Options ----------\n" +
				"Character Selected: $characterString\n" +
				"Support(s) Selected: $supportCardListString\n\n" +
				"---------- Tesseract OCR Optimization ----------\n" +
				"OCR Threshold: $threshold\n" +
				"Enable Automatic OCR retry: $enableAutomaticRetryString\n" +
				"Minimum OCR Confidence: $confidence\n\n" +
				"---------- Misc Options ----------\n" +
				"Hide String Comparison Results: $hideComparisonResultsString\n" +
				"Debug Mode: $enableDebugModeString"
		
		// Enable the start button if the required settings have been set.
		startButton.isEnabled = (characterString != "Please select one in the Settings" || characterString == "All Characters Selected")
		
		// Now construct the data files if this is the first time.
		if (firstRun) {
			constructDataClasses()
			firstRun = false
		}
		
		return homeFragmentView
	}
	
	override fun onResume() {
		super.onResume()
		
		// Update the button's text depending on if the MediaProjection service is running.
		if (!firstBoot) {
			if (MediaProjectionService.isRunning) {
				startButton.text = getString(R.string.stop)
			} else {
				startButton.text = getString(R.string.start)
			}
		}
		
		// Setting this false here will ensure that stopping the MediaProjection Service outside of this application will update this button's text.
		firstBoot = false
		
		// Now update the Message Log inside the ScrollView with the latest logging messages from the bot.
		Log.d(TAG, "Now updating the Message Log TextView...")
		val messageLogTextView = homeFragmentView.findViewById<TextView>(R.id.message_log)
		messageLogTextView.text = ""
		var index = 0
		
		// Get local copies of the message log.
		val messageLog = MessageLog.messageLog
		val messageLogSize = MessageLog.messageLog.size
		while (index < messageLogSize) {
			messageLogTextView.append("\n" + messageLog[index])
			index += 1
		}
		
		// Set up the app updater to check for the latest update from GitHub.
		AppUpdater(myContext)
			.setUpdateFrom(UpdateFrom.XML)
			.setUpdateXML("https://raw.githubusercontent.com/steve1316/uma-android-training-helper/main/app/update.xml")
			.start()
	}
	
	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
		if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
			// Start up the MediaProjection service after the user accepts the onscreen prompt.
			myContext.startService(data?.let { MediaProjectionService.getStartIntent(myContext, resultCode, data) })
		}
	}
	
	/**
	 * Checks to see if the application is ready to start.
	 *
	 * @return True if the application has overlay permission and has enabled the Accessibility Service for it. Otherwise, return False.
	 */
	private fun startReadyCheck(): Boolean {
		if (!checkForOverlayPermission()) {
			return false
		}
		
		return true
	}
	
	/**
	 * Starts the MediaProjection Service.
	 */
	private fun startProjection() {
		val mediaProjectionManager = context?.getSystemService(Context.MEDIA_PROJECTION_SERVICE) as MediaProjectionManager
		startActivityForResult(mediaProjectionManager.createScreenCaptureIntent(), 100)
	}
	
	/**
	 * Stops the MediaProjection Service.
	 */
	private fun stopProjection() {
		context?.startService(MediaProjectionService.getStopIntent(requireContext()))
	}
	
	/**
	 * Checks if the application has permission to draw overlays. If not, it will direct the user to enable it.
	 *
	 * Source is from https://github.com/Fate-Grand-Automata/FGA/blob/master/app/src/main/java/com/mathewsachin/fategrandautomata/ui/MainFragment.kt
	 *
	 * @return True if it has permission. False otherwise.
	 */
	private fun checkForOverlayPermission(): Boolean {
		if (!Settings.canDrawOverlays(requireContext())) {
			Log.d(TAG, "Application is missing overlay permission.")
			
			AlertDialog.Builder(requireContext()).apply {
				setTitle(R.string.overlay_disabled)
				setMessage(R.string.overlay_disabled_message)
				setPositiveButton(R.string.go_to_settings) { _, _ ->
					// Send the user to the Overlay Settings.
					val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:${requireContext().packageName}"))
					startActivity(intent)
				}
				setNegativeButton(android.R.string.cancel, null)
			}.show()
			
			return false
		}
		
		Log.d(TAG, "Application has permission to draw overlay.")
		return true
	}
	
	/**
	 * Construct the data classes associated with Characters, Support Cards and Skills from the provided JSON data files.
	 */
	private fun constructDataClasses() {
		// Construct the data class for Characters and Support Cards.
		val fileList = arrayListOf("characters.json", "supports.json")
		while (fileList.size > 0) {
			val fileName = fileList[0]
			fileList.removeAt(0)
			val objectString = myContext.assets.open("data/$fileName").bufferedReader().use { it.readText() }
			
			JsonReader(StringReader(objectString)).use { reader ->
				reader.beginObject {
					while (reader.hasNext()) {
						// Grab the name.
						val name = reader.nextName()
						
						// Now iterate through each event and collect all of them and their option rewards into a map.
						val eventOptionRewards = mutableMapOf<String, ArrayList<String>>()
						reader.beginObject {
							while (reader.hasNext()) {
								// Grab the event name.
								val eventName = reader.nextName()
								eventOptionRewards.putIfAbsent(eventName, arrayListOf())
								
								reader.beginArray {
									// Grab all of the event option rewards for this event and add them to the map.
									while (reader.hasNext()) {
										val optionReward = reader.nextString()
										eventOptionRewards[eventName]?.add(optionReward)
									}
								}
							}
						}
						
						// Finally, put into the MutableMap the key value pair depending on the current category.
						if (fileName == "characters.json") {
							CharacterData.characters[name] = eventOptionRewards
						} else {
							SupportData.supports[name] = eventOptionRewards
						}
					}
				}
			}
		}
		
		// Now construct the data class for Skills.
		val objectString = myContext.assets.open("data/skills.json").bufferedReader().use { it.readText() }
		JsonReader(StringReader(objectString)).use { reader ->
			reader.beginObject {
				while (reader.hasNext()) {
					// Grab the name.
					val skillName = reader.nextName()
					SkillData.skills.putIfAbsent(skillName, mutableMapOf())
					
					reader.beginObject {
						// Skip the id.
						reader.nextName()
						reader.nextInt()
						
						// Grab the English name and description.
						reader.nextName()
						val skillEnglishName = reader.nextString()
						reader.nextName()
						val skillEnglishDescription = reader.nextString()
						
						// Finally, collect them into a map and put them into the data class.
						val tempMap = mutableMapOf<String, String>()
						tempMap["englishName"] = skillEnglishName
						tempMap["englishDescription"] = skillEnglishDescription
						SkillData.skills[skillName] = tempMap
					}
				}
			}
		}
	}
}
