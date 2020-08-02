package com.trivago.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.trivago.core.data.models.Film
import com.trivago.core.data.models.Planet
import com.trivago.core.data.models.Species
import com.trivago.data.repository.CharacterFilmsRepository
import com.trivago.data.repository.CharacterPlanetRepository
import com.trivago.data.repository.CharacterSpeciesRepository

class CharacterDetailsViewModel(
    private val characterSpeciesRepository: CharacterSpeciesRepository,
    private val characterFilmsRepository: CharacterFilmsRepository,
    private val characterPlanetRepository: CharacterPlanetRepository
) : ViewModel() {

    suspend fun fetchSpecies(characterUrl: String): LiveData<List<Species>> =
        characterSpeciesRepository.fetchSpecies(characterUrl).asLiveData()

    suspend fun fetchFilms(characterUrl: String): LiveData<List<Film>> =
        characterFilmsRepository.fetchFilms(characterUrl).asLiveData()

    suspend fun fetchPlanet(characterUrl: String): LiveData<Planet> =
        characterPlanetRepository.fetchPlanet(characterUrl).asLiveData()
}