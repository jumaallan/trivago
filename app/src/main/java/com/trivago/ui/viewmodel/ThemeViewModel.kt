package com.trivago.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.trivago.core.utils.TrivagoSharedPreferenceLiveData
import com.trivago.data.repository.ThemeRepository

/**
 * ThemeViewModel
 *
 * This viewmodel is responsible for handling theme changes from the ThemeRepository
 * @param themeRepository
 */
class ThemeViewModel(
    private val themeRepository: ThemeRepository
) : ViewModel() {

    /**
     * getAppTheme is responsible for listening in on theme changes
     *
     * @return a TrivagoSharedPreferenceLiveData that can be observed on the UI
     */
    fun getAppTheme(): TrivagoSharedPreferenceLiveData =
        themeRepository.getAppTheme()
}