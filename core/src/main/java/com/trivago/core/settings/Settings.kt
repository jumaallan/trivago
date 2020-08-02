package com.trivago.core.settings

import android.content.SharedPreferences
import androidx.core.content.edit

/**
 *  Shared Preferences Settings
 *
 *  This class manages shared preferences globally, for the app and any other module that's introduced
 *
 *  @param settings
 *  @constructor creates an instance of the Settings class
 */
class Settings(
    val settings: SharedPreferences
) {

    /**
     * Fetches the saved bearer token
     *
     * @return the bearer token, as a nullable string
     */
    fun getBearerToken(): String? = settings.getString(BEARER_TOKEN_KEY, "")

    /**
     * Saves the bearer token
     *
     * @param token
     */
    fun setBearerToken(token: String) {
        settings.edit {
            putString(BEARER_TOKEN_KEY, token)
        }
    }

    /**
     * Fetches the current app theme
     *
     * @return returns the app them, as a string
     */
    fun getAppTheme(): String =
        settings.getString(BEARER_TOKEN_KEY, PREFERENCE_THEME_DEF_VAL).toString()

    /**
     * Saves the app theme
     *
     * @param theme
     */
    fun setAppTheme(theme: String) {
        settings.edit {
            putString(PREFERENCE_THEME_KEY, PREFERENCE_THEME_DEF_VAL)
        }
    }

    /**
     * Responsible for clearing the shared preferences file
     */
    fun clearData() {
        settings.edit { clear() }
    }

    companion object SettingsConstants {
        const val TRIVAGO_SETTINGS_NAME = "trivago_settings"
        const val BEARER_TOKEN_KEY = "bearer_token"

        const val PREFERENCE_THEME_KEY = "theme"
        const val PREFERENCE_THEME_DEF_VAL = "System"
    }
}