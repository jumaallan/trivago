package com.trivago.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.trivago.core.data.models.CharacterResponse
import com.trivago.data.repository.CharacterSearchRepository
import kotlinx.coroutines.flow.Flow

class CharacterSearchViewModel(
    private val characterSearchRepository: CharacterSearchRepository
) : ViewModel() {

    suspend fun searchStarWarsCharacters(characterName: String): Flow<List<CharacterResponse>> =
        characterSearchRepository.searchStarWarsCharacters(characterName)
}