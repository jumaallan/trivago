package com.trivago.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.trivago.data.repository.CharacterSearchRepository

class CharacterSearchViewModel(
    private val characterSearchRepository: CharacterSearchRepository
) : ViewModel()