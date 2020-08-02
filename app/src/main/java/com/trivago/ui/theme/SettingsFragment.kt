package com.trivago.ui.theme

import android.os.Build
import android.os.Bundle
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.trivago.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
        val themePreference: ListPreference? = findPreference(THEME_KEY)

        val themeArrayOptionsAboveQ = resources.getStringArray(R.array.theme_options_above_q)
        val themeArrayOptionsBelowQ = resources.getStringArray(R.array.theme_options_below_q)
        themePreference?.entries =
            if (Build.VERSION.SDK_INT >= 29) themeArrayOptionsAboveQ else themeArrayOptionsBelowQ

        themePreference?.summaryProvider =
            Preference.SummaryProvider<ListPreference> { preference ->
                when (preference.value) {
                    "Light" -> themeArrayOptionsAboveQ[0]
                    "Dark" -> themeArrayOptionsAboveQ[1]
                    "System" -> if (Build.VERSION.SDK_INT >= 29) themeArrayOptionsAboveQ[2] else themeArrayOptionsBelowQ[2]
                    else -> getString(R.string.def)
                }
            }
    }

    companion object {
        const val THEME_KEY = "theme"
    }

}
