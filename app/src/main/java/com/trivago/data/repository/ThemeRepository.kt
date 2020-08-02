package com.trivago.data.repository

import com.trivago.core.settings.Settings
import com.trivago.core.utils.TrivagoSharedPreferenceLiveData

/**
 * ThemeRepository
 *
 * This repository class is responsible for observing the app theme, and its changes
 *
 * @param settings
 */
class ThemeRepository(
    private val settings: Settings
) {

    /**
     * getAppTheme is responsible for listening to live changes on the app theme preference, on the shared preference file
     *
     * @return a TrivagoSharedPreferenceLiveData that can be observed on the UI
     */
    fun getAppTheme(): TrivagoSharedPreferenceLiveData = TrivagoSharedPreferenceLiveData(
        sharedPreferences = settings.settings,
        key = Settings.PREFERENCE_THEME_KEY,
        defValue = settings.getAppTheme()
    )
}