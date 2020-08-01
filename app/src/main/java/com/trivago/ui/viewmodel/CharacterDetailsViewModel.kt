package com.trivago.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.trivago.core.data.models.Film
import com.trivago.core.data.models.Planet
import com.trivago.core.data.models.Species
import com.trivago.data.repository.CharacterDetailsRepository
import kotlinx.coroutines.flow.Flow

class CharacterDetailsViewModel(
    private val characterDetailsRepository: CharacterDetailsRepository
) : ViewModel() {

    suspend fun fetchSpecies(characterUrl: String): Flow<List<Species>> =
        characterDetailsRepository.fetchSpecies(characterUrl)

    suspend fun fetchFilms(characterUrl: String): Flow<List<Film>> =
        characterDetailsRepository.fetchFilms(characterUrl)

    suspend fun fetchPlanet(characterUrl: String): Flow<Planet> =
        characterDetailsRepository.fetchPlanet(characterUrl)
}