package com.trivago.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trivago.data.model.CharacterResponse
import com.trivago.data.repository.CharacterFilmsRepository
import com.trivago.data.repository.CharacterPlanetRepository
import com.trivago.data.repository.CharacterSpeciesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharacterDetailsViewModel(
    private val characterSpeciesRepository: CharacterSpeciesRepository,
    private val characterFilmsRepository: CharacterFilmsRepository,
    private val characterPlanetRepository: CharacterPlanetRepository
) : ViewModel() {

    val characterResponseState: LiveData<CharacterResponse>
        get() = characterResponse

    private var characterResponse = MutableLiveData<CharacterResponse>()

    init {
        characterResponse.value = CharacterResponse(
            null,
            null,
            null
        )
    }

    @FlowPreview
    fun getCharacterDetails(characterUrl: String) {
        viewModelScope.launch {
            withContext(Dispatchers.Main) { fetchPlanet(characterUrl) }
            withContext(Dispatchers.Main) { fetchFilms(characterUrl) }
            withContext(Dispatchers.Main) { fetchSpecies(characterUrl) }
        }
    }

    @FlowPreview
    private suspend fun fetchSpecies(characterUrl: String) {
        characterResponse.value = characterResponse.value?.copy(
            species = characterSpeciesRepository.fetchSpecies(characterUrl).flattenToList()
        )
    }

    @FlowPreview
    private suspend fun fetchFilms(characterUrl: String) {
        characterResponse.value = characterResponse.value?.copy(
            films = characterFilmsRepository.fetchFilms(characterUrl).flattenToList()
        )
    }

    private suspend fun fetchPlanet(characterUrl: String) {
        characterResponse.value = characterResponse.value?.copy(
            planet = characterPlanetRepository.fetchPlanet(characterUrl)
        )
    }

    @FlowPreview
    private suspend fun <T> Flow<List<T>>.flattenToList() =
        flatMapConcat { it.asFlow() }.toList()
}