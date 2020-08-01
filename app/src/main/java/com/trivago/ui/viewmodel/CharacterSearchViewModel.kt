package com.trivago.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.trivago.core.data.network.CharacterResponse
import com.trivago.data.model.Character
import com.trivago.data.repository.CharacterSearchRepository
import kotlinx.coroutines.flow.Flow

class CharacterSearchViewModel(
    private val characterSearchRepository: CharacterSearchRepository
) : ViewModel() {

    suspend fun searchStarWarsCharacters(characterName: String): Flow<List<CharacterResponse>> =
        characterSearchRepository.searchStarWarsCharacters(characterName)

    suspend fun saveCharacter(character: Character) =
        characterSearchRepository.saveCharacter(character)

    fun getCharacters(): LiveData<List<Character>> =
        characterSearchRepository.getCharacters().asLiveData()
}