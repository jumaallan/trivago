package com.trivago.core.settings

import android.content.SharedPreferences
import androidx.core.content.edit

class Settings(
    val settings: SharedPreferences
) {

    fun getBearerToken(): String? {
        return settings.getString(SettingsConstants.BEARER_TOKEN_KEY, "")
    }

    fun setBearerToken(token: String) {
        settings.edit {
            putString(SettingsConstants.BEARER_TOKEN_KEY, token)
        }
    }

    fun clearData() {
        settings.edit { clear() }
    }
}

object SettingsConstants {
    const val TRIVAGO_SETTINGS_NAME = "trivago_settings"
    const val BEARER_TOKEN_KEY = "bearer_token"
}