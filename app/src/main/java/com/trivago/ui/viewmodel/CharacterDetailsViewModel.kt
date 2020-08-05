package com.trivago.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trivago.core.utils.flattenToList
import com.trivago.data.model.CharacterResponse
import com.trivago.data.repository.CharacterFilmsRepository
import com.trivago.data.repository.CharacterPlanetRepository
import com.trivago.data.repository.CharacterSpeciesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * CharacterDetailsViewModel
 *
 * This viewmodel is responsible for handling character details from various repositories
 * @param characterSpeciesRepository
 * @param characterFilmsRepository
 * @param characterPlanetRepository
 *
 */
class CharacterDetailsViewModel(
    private val characterSpeciesRepository: CharacterSpeciesRepository,
    private val characterFilmsRepository: CharacterFilmsRepository,
    private val characterPlanetRepository: CharacterPlanetRepository
) : ViewModel() {

    val characterResponseState: LiveData<CharacterResponse>
        get() = characterResponse

    private var characterResponse =
        MutableLiveData<CharacterResponse>(CharacterResponse(null, null, null))


    /**
     * Responsible for initiating a call to fetch the character details
     *      - fetch planet details
     *      - fetch films list
     *      - fetch species list
     *
     * @param characterUrl
     */
    @FlowPreview
    fun getCharacterDetails(characterUrl: String) {
        viewModelScope.launch {
            withContext(Dispatchers.Main) { fetchPlanet(characterUrl) }
            withContext(Dispatchers.Main) { fetchFilms(characterUrl) }
            withContext(Dispatchers.Main) { fetchSpecies(characterUrl) }
        }
    }

    /**
     * Responsible for fetching species from the species repo, and saving the response to characterResponse
     *
     * @param characterUrl
     */
    @FlowPreview
    private suspend fun fetchSpecies(characterUrl: String) {
        characterResponse.value = characterResponse.value?.copy(
            species = characterSpeciesRepository.fetchSpecies(characterUrl).flattenToList()
        )
    }

    /**
     * Responsible for fetching films from the films repo, and saving the response to characterResponse
     *
     * @param characterUrl
     */
    @FlowPreview
    private suspend fun fetchFilms(characterUrl: String) {
        characterResponse.value = characterResponse.value?.copy(
            films = characterFilmsRepository.fetchFilms(characterUrl).flattenToList()
        )
    }

    /**
     * Responsible for fetching planets from the planets repo, and saving the response to characterResponse
     *
     * @param characterUrl
     */
    private suspend fun fetchPlanet(characterUrl: String) {
        characterResponse.value = characterResponse.value?.copy(
            planet = characterPlanetRepository.fetchPlanet(characterUrl)
        )
    }
}