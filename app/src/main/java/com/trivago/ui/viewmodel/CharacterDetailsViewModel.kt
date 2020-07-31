package com.trivago.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.trivago.data.repository.CharacterDetailsRepository

class CharacterDetailsViewModel(
    private val characterDetailsRepository: CharacterDetailsRepository
) : ViewModel()
