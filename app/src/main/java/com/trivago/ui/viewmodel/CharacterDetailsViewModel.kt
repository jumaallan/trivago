package com.trivago.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.trivago.core.data.models.Film
import com.trivago.core.data.models.Planet
import com.trivago.core.data.models.Species
import com.trivago.data.repository.CharacterFilmsRepository
import com.trivago.data.repository.CharacterPlanetRepository
import com.trivago.data.repository.CharacterSpeciesRepository
import kotlinx.coroutines.flow.Flow

class CharacterDetailsViewModel(
    private val characterSpeciesRepository: CharacterSpeciesRepository,
    private val characterFilmsRepository: CharacterFilmsRepository,
    private val characterPlanetRepository: CharacterPlanetRepository
) : ViewModel() {

    suspend fun fetchSpecies(characterUrl: String): Flow<List<Species>> =
        characterSpeciesRepository.fetchSpecies(characterUrl)

    suspend fun fetchFilms(characterUrl: String): Flow<List<Film>> =
        characterFilmsRepository.fetchFilms(characterUrl)

    suspend fun fetchPlanet(characterUrl: String): Flow<Planet> =
        characterPlanetRepository.fetchPlanet(characterUrl)
}