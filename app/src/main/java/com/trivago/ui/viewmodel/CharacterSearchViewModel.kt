package com.trivago.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.trivago.core.data.models.StarWarsCharacter
import com.trivago.data.model.Character
import com.trivago.data.repository.CharacterSearchRepository

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
    suspend fun saveCharacter(character: Character) =
        characterSearchRepository.saveCharacter(character)

    /**
     * Responsible for fetching all characters from the repos - for search suggestions purposes
     *
     * @return a livedata list of the characters
     */
    fun getCharacters(): LiveData<List<Character>> =
        characterSearchRepository.getCharacters().asLiveData()
}