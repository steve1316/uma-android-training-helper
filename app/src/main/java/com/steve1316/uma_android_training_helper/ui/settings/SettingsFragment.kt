package com.steve1316.uma_android_training_helper.ui.settings

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.core.content.edit
import androidx.preference.*
import com.steve1316.uma_android_training_helper.R

class SettingsFragment : PreferenceFragmentCompat() {
	private val TAG: String = "UATH_SettingsFragment"
	
	private lateinit var sharedPreferences: SharedPreferences
	
	private lateinit var builder: AlertDialog.Builder
	
	private lateinit var rItems: Array<String>
	private lateinit var rCheckedItems: BooleanArray
	private var userSelectedROptions: ArrayList<Int> = arrayListOf()
	private lateinit var srItems: Array<String>
	private lateinit var srCheckedItems: BooleanArray
	private var userSelectedSROptions: ArrayList<Int> = arrayListOf()
	private lateinit var ssrItems: Array<String>
	private lateinit var ssrCheckedItems: BooleanArray
	private var userSelectedSSROptions: ArrayList<Int> = arrayListOf()
	
	companion object {
		/**
		 * Get a String value from the SharedPreferences using the provided key.
		 *
		 * @param context The context for the application.
		 * @param key The name of the preference to retrieve.
		 * @return The value that is associated with the key.
		 */
		fun getStringSharedPreference(context: Context, key: String): String {
			val sharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
			return sharedPreferences.getString(key, "")!!
		}
		
		/**
		 * Get a Set<String> value from the SharedPreferences using the provided key.
		 *
		 * @param context The context for the application.
		 * @param key The name of the preference to retrieve.
		 * @return The value that is associated with the key.
		 */
		fun getStringSetSharedPreference(context: Context, key: String): Set<String> {
			val sharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
			return sharedPreferences.getStringSet(key, setOf())!!
		}
		
		/**
		 * Get a Int value from the SharedPreferences using the provided key.
		 *
		 * @param context The context for the application.
		 * @param key The name of the preference to retrieve.
		 * @return The value that is associated with the key.
		 */
		fun getIntSharedPreference(context: Context, key: String): Int {
			val sharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
			return sharedPreferences.getInt(key, 230)
		}
		
		/**
		 * Get a Boolean value from the SharedPreferences using the provided key.
		 *
		 * @param context The context for the application.
		 * @param key The name of the preference to retrieve.
		 * @return The value that is associated with the key.
		 */
		fun getBooleanSharedPreference(context: Context, key: String): Boolean {
			val sharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
			return sharedPreferences.getBoolean(key, false)
		}
	}
	
	// This listener is triggered whenever the user changes a Preference setting in the Settings Page.
	private val onSharedPreferenceChangeListener = SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences, key ->
		val characterPicker: ListPreference = findPreference("characterPicker")!!
		val hideResultsCheckBox: CheckBoxPreference = findPreference("hideResultsCheckBox")!!
		val thresholdSeekBar: SeekBarPreference = findPreference("thresholdSeekBar")!!
		
		if (key != null) {
			// Note that is no need to handle the Preference that allows multiple selection here as it is already handled in its own function.
			when (key) {
				"characterPicker" -> {
					sharedPreferences.edit {
						putString("character", characterPicker.value)
						commit()
					}
				}
				"hideResultsCheckBox" -> {
					sharedPreferences.edit {
						putBoolean("hideResults", hideResultsCheckBox.isChecked)
						commit()
					}
				}
				"thresholdSeekBar" -> {
					sharedPreferences.edit {
						putInt("threshold", thresholdSeekBar.value)
						commit()
					}
				}
			}
		}
	}
	
	override fun onResume() {
		super.onResume()
		
		// Makes sure that OnSharedPreferenceChangeListener works properly and avoids the situation where the app suddenly stops triggering the listener.
		preferenceScreen.sharedPreferences.registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener)
	}
	
	override fun onPause() {
		super.onPause()
		preferenceScreen.sharedPreferences.unregisterOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener)
	}
	
	// This function is called right after the user navigates to the SettingsFragment.
	override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
		// Display the layout using the preferences xml.
		setPreferencesFromResource(R.xml.preferences, rootKey)
		
		// Get the SharedPreferences.
		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
		
		// Grab the saved preferences from the previous time the user used the app.
		val character = sharedPreferences.getString("character", "")
		val threshold = sharedPreferences.getInt("threshold", 230)
		val hideResults = sharedPreferences.getBoolean("hideResults", false)
		
		// Get references to the Preference components.
		val characterPicker: ListPreference = findPreference("characterPicker")!!
		val thresholdSeekBar: SeekBarPreference = findPreference("thresholdSeekBar")!!
		val hideResultsCheckBox: CheckBoxPreference = findPreference("hideResultsCheckBox")!!
		
		// Now set the following values from the shared preferences. Work downwards through the Preferences and make the next ones enabled to direct user's attention as they go through the settings
		// down the page.
		
		if (character != null && character.isNotEmpty()) {
			characterPicker.value = character
		}
		
		// Populate the lists in the following rarity multi-pickers.
		createSupportCardPicker("R")
		createSupportCardPicker("SR")
		createSupportCardPicker("SSR")
		
		thresholdSeekBar.value = threshold
		
		hideResultsCheckBox.isChecked = hideResults
		
		Log.d(TAG, "Preferences created successfully.")
	}
	
	/**
	 * Build the Multi-Picker Alert Dialog for the specified rarity Support Cards.
	 */
	private fun createSupportCardPicker(rarity: String) {
		when (rarity) {
			"R" -> {
				val multiplePreference: Preference = findPreference("supportRPicker")!!
				val savedOptions = sharedPreferences.getString("supportRList", "")!!.split("|")
				val selectedOptions = sharedPreferences.getString("selectedROptions", "")!!.split("|")
				
				// Update the Preference's summary to reflect the order of options selected if the user did it before.
				if (savedOptions.toList().isEmpty() || savedOptions.toList()[0] == "") {
					multiplePreference.summary = "Select your chosen R Support Card(s)."
				} else {
					multiplePreference.summary = "${savedOptions.toList()}"
				}
				
				multiplePreference.setOnPreferenceClickListener {
					// Create the AlertDialog that pops up after clicking on this Preference.
					builder = AlertDialog.Builder(context)
					builder.setTitle("Select Option(s)")
					
					// Grab the Support Card items corresponding to the rarity.
					rItems = resources.getStringArray(R.array.support_list_r)
					
					// Populate the list for multiple options if this is the first time.
					if (savedOptions.isEmpty() || savedOptions[0] == "") {
						rCheckedItems = BooleanArray(rItems.size)
						var index = 0
						rItems.forEach { _ ->
							rCheckedItems[index] = false
							index++
						}
					} else {
						rCheckedItems = BooleanArray(rItems.size)
						var index = 0
						rItems.forEach {
							// Populate the checked items BooleanArray with true or false depending on what the user selected before.
							rCheckedItems[index] = savedOptions.contains(it)
							index++
						}
						
						// Repopulate the user selected options according to its order selected.
						userSelectedROptions.clear()
						selectedOptions.forEach {
							userSelectedROptions.add(it.toInt())
						}
					}
					
					// Set the selectable items for this AlertDialog.
					builder.setMultiChoiceItems(rItems, rCheckedItems) { _, position, isChecked ->
						if (isChecked) {
							userSelectedROptions.add(position)
						} else {
							userSelectedROptions.remove(position)
						}
					}
					
					// Set the AlertDialog's PositiveButton.
					builder.setPositiveButton("OK") { _, _ ->
						// Grab the options using the acquired indexes. This will put them in order from the user's highest to lowest priority.
						val values: ArrayList<String> = arrayListOf()
						
						userSelectedROptions.forEach {
							values.add(rItems[it])
						}
						
						// Join the elements together into a String with the "|" delimiter in order to keep its order when storing into SharedPreferences.
						val newValues = values.joinToString("|")
						val newSelectedOptions = userSelectedROptions.joinToString("|")
						
						// Note: putStringSet does not support ordering or duplicate values. If you need ordering/duplicate values, either concatenate the values together as a String separated by a
						// delimiter or think of another way.
						sharedPreferences.edit {
							putString("supportRList", newValues)
							putString("selectedROptions", newSelectedOptions)
							apply()
						}
						
						// Recreate the AlertDialog again to update it with the newly selected items.
						createSupportCardPicker("R")
						
						if (values.toList().isEmpty()) {
							multiplePreference.summary = "Select your chosen R Support Card(s)."
						} else {
							multiplePreference.summary = "${values.toList()}"
						}
					}
					
					// Set the AlertDialog's NegativeButton.
					builder.setNegativeButton("Dismiss") { dialog, _ -> dialog?.dismiss() }
					
					// Set the AlertDialog's NeutralButton.
					builder.setNeutralButton("Clear all") { _, _ ->
						// Go through every checked item and set them to false.
						for (i in rCheckedItems.indices) {
							rCheckedItems[i] = false
						}
						
						// After that, clear the list of user-selected options and the one in SharedPreferences.
						userSelectedROptions.clear()
						sharedPreferences.edit {
							remove("supportRList")
							apply()
						}
						
						// Recreate the AlertDialog again to update it with the newly selected items and reset its summary.
						createSupportCardPicker("R")
						multiplePreference.summary = "Select your chosen R Support Card(s)."
					}
					
					// Finally, show the AlertDialog to the user.
					builder.create().show()
					
					true
				}
			}
			"SR" -> {
				val multiplePreference: Preference = findPreference("supportSRPicker")!!
				val savedOptions = sharedPreferences.getString("supportSRList", "")!!.split("|")
				val selectedOptions = sharedPreferences.getString("selectedSROptions", "")!!.split("|")
				
				// Update the Preference's summary to reflect the order of options selected if the user did it before.
				if (savedOptions.toList().isEmpty() || savedOptions.toList()[0] == "") {
					multiplePreference.summary = "Select your chosen SR Support Card(s)."
				} else {
					multiplePreference.summary = "${savedOptions.toList()}"
				}
				
				multiplePreference.setOnPreferenceClickListener {
					// Create the AlertDialog that pops up after clicking on this Preference.
					builder = AlertDialog.Builder(context)
					builder.setTitle("Select Option(s)")
					
					// Grab the Support Card items corresponding to the rarity.
					srItems = resources.getStringArray(R.array.support_list_sr)
					
					// Populate the list for multiple options if this is the first time.
					if (savedOptions.isEmpty() || savedOptions[0] == "") {
						srCheckedItems = BooleanArray(srItems.size)
						var index = 0
						srItems.forEach { _ ->
							srCheckedItems[index] = false
							index++
						}
					} else {
						srCheckedItems = BooleanArray(srItems.size)
						var index = 0
						srItems.forEach {
							// Populate the checked items BooleanArray with true or false depending on what the user selected before.
							srCheckedItems[index] = savedOptions.contains(it)
							index++
						}
						
						// Repopulate the user selected options according to its order selected.
						userSelectedSROptions.clear()
						selectedOptions.forEach {
							userSelectedSROptions.add(it.toInt())
						}
					}
					
					// Set the selectable items for this AlertDialog.
					builder.setMultiChoiceItems(srItems, srCheckedItems) { _, position, isChecked ->
						if (isChecked) {
							userSelectedSROptions.add(position)
						} else {
							userSelectedSROptions.remove(position)
						}
					}
					
					// Set the AlertDialog's PositiveButton.
					builder.setPositiveButton("OK") { _, _ ->
						// Grab the options using the acquired indexes. This will put them in order from the user's highest to lowest priority.
						val values: ArrayList<String> = arrayListOf()
						
						userSelectedSROptions.forEach {
							values.add(srItems[it])
						}
						
						// Join the elements together into a String with the "|" delimiter in order to keep its order when storing into SharedPreferences.
						val newValues = values.joinToString("|")
						val newSelectedOptions = userSelectedSROptions.joinToString("|")
						
						// Note: putStringSet does not support ordering or duplicate values. If you need ordering/duplicate values, either concatenate the values together as a String separated by a
						// delimiter or think of another way.
						sharedPreferences.edit {
							putString("supportSRList", newValues)
							putString("selectedSROptions", newSelectedOptions)
							apply()
						}
						
						// Recreate the AlertDialog again to update it with the newly selected items.
						createSupportCardPicker("SR")
						
						if (values.toList().isEmpty()) {
							multiplePreference.summary = "Select your chosen SR Support Card(s)."
						} else {
							multiplePreference.summary = "${values.toList()}"
						}
					}
					
					// Set the AlertDialog's NegativeButton.
					builder.setNegativeButton("Dismiss") { dialog, _ -> dialog?.dismiss() }
					
					// Set the AlertDialog's NeutralButton.
					builder.setNeutralButton("Clear all") { _, _ ->
						// Go through every checked item and set them to false.
						for (i in srCheckedItems.indices) {
							srCheckedItems[i] = false
						}
						
						// After that, clear the list of user-selected options and the one in SharedPreferences.
						userSelectedSROptions.clear()
						sharedPreferences.edit {
							remove("supportSRList")
							apply()
						}
						
						// Recreate the AlertDialog again to update it with the newly selected items and reset its summary.
						createSupportCardPicker("SR")
						multiplePreference.summary = "Select your chosen SR Support Card(s)."
					}
					
					// Finally, show the AlertDialog to the user.
					builder.create().show()
					
					true
				}
			}
			else -> {
				val multiplePreference: Preference = findPreference("supportSSRPicker")!!
				val savedOptions = sharedPreferences.getString("supportSSRList", "")!!.split("|")
				val selectedOptions = sharedPreferences.getString("selectedSSROptions", "")!!.split("|")
				
				// Update the Preference's summary to reflect the order of options selected if the user did it before.
				if (savedOptions.toList().isEmpty() || savedOptions.toList()[0] == "") {
					multiplePreference.summary = "Select your chosen SSR Support Card(s)."
				} else {
					multiplePreference.summary = "${savedOptions.toList()}"
				}
				
				multiplePreference.setOnPreferenceClickListener {
					// Create the AlertDialog that pops up after clicking on this Preference.
					builder = AlertDialog.Builder(context)
					builder.setTitle("Select Option(s)")
					
					// Grab the Support Card items corresponding to the rarity.
					ssrItems = resources.getStringArray(R.array.support_list_ssr)
					
					// Populate the list for multiple options if this is the first time.
					if (savedOptions.isEmpty() || savedOptions[0] == "") {
						ssrCheckedItems = BooleanArray(ssrItems.size)
						var index = 0
						ssrItems.forEach { _ ->
							ssrCheckedItems[index] = false
							index++
						}
					} else {
						ssrCheckedItems = BooleanArray(ssrItems.size)
						var index = 0
						ssrItems.forEach {
							// Populate the checked items BooleanArray with true or false depending on what the user selected before.
							ssrCheckedItems[index] = savedOptions.contains(it)
							index++
						}
						
						// Repopulate the user selected options according to its order selected.
						userSelectedSSROptions.clear()
						selectedOptions.forEach {
							userSelectedSSROptions.add(it.toInt())
						}
					}
					
					// Set the selectable items for this AlertDialog.
					builder.setMultiChoiceItems(ssrItems, ssrCheckedItems) { _, position, isChecked ->
						if (isChecked) {
							userSelectedSSROptions.add(position)
						} else {
							userSelectedSSROptions.remove(position)
						}
					}
					
					// Set the AlertDialog's PositiveButton.
					builder.setPositiveButton("OK") { _, _ ->
						// Grab the options using the acquired indexes. This will put them in order from the user's highest to lowest priority.
						val values: ArrayList<String> = arrayListOf()
						
						userSelectedSSROptions.forEach {
							values.add(ssrItems[it])
						}
						
						// Join the elements together into a String with the "|" delimiter in order to keep its order when storing into SharedPreferences.
						val newValues = values.joinToString("|")
						val newSelectedOptions = userSelectedSSROptions.joinToString("|")
						
						// Note: putStringSet does not support ordering or duplicate values. If you need ordering/duplicate values, either concatenate the values together as a String separated by a
						// delimiter or think of another way.
						sharedPreferences.edit {
							putString("supportSSRList", newValues)
							putString("selectedSSROptions", newSelectedOptions)
							apply()
						}
						
						// Recreate the AlertDialog again to update it with the newly selected items.
						createSupportCardPicker("SSR")
						
						if (values.toList().isEmpty()) {
							multiplePreference.summary = "Select your chosen SSR Support Card(s)."
						} else {
							multiplePreference.summary = "${values.toList()}"
						}
					}
					
					// Set the AlertDialog's NegativeButton.
					builder.setNegativeButton("Dismiss") { dialog, _ -> dialog?.dismiss() }
					
					// Set the AlertDialog's NeutralButton.
					builder.setNeutralButton("Clear all") { _, _ ->
						// Go through every checked item and set them to false.
						for (i in ssrCheckedItems.indices) {
							ssrCheckedItems[i] = false
						}
						
						// After that, clear the list of user-selected options and the one in SharedPreferences.
						userSelectedSSROptions.clear()
						sharedPreferences.edit {
							remove("supportSSRList")
							apply()
						}
						
						// Recreate the AlertDialog again to update it with the newly selected items and reset its summary.
						createSupportCardPicker("SSR")
						multiplePreference.summary = "Select your chosen SSR Support Card(s)."
					}
					
					// Finally, show the AlertDialog to the user.
					builder.create().show()
					
					true
				}
			}
		}
	}
}