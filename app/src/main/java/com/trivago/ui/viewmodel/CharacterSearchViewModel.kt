package com.trivago.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.trivago.core.data.models.StarWarsCharacter
import com.trivago.data.model.Character
import com.trivago.data.repository.CharacterSearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * CharacterSearchViewModel
 *
 * This viewmodel is responsible for handling character search data from the CharacterSearchRepository
 * @param characterSearchRepository
 */
class CharacterSearchViewModel(
    private val characterSearchRepository: CharacterSearchRepository
) : ViewModel() {

    /**
     * searchStarWarsCharacters is responsible for the fetching the star wars characters from the CharacterSearchRepository
     *
     * @param characterName
     * @return a livedata object of star wars character
     */
    suspend fun searchStarWarsCharacters(characterName: String): LiveData<List<StarWarsCharacter>> =
        characterSearchRepository.searchStarWarsCharacters(characterName).asLiveData()

    /**
     * Responsible for passing a character to the repo, to save to the database
     *
     * @param character
     */
    fun saveCharacter(character: Character) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                characterSearchRepository.saveCharacter(character)
            }
        }
    }

    /**
     * Responsible for fetching all characters from the repos - for search suggestions purposes
     *
     * @return a livedata list of the characters
     */
    fun getCharacters(): LiveData<List<Character>> =
        characterSearchRepository.getCharacters().asLiveData()

    /**
     * Responsible for passing characters to the repo, to save to the database
     *
     * @param characters
     */
    fun saveCharacters(characters: List<Character>) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                characterSearchRepository.saveCharacters(characters)
            }
        }
    }
}