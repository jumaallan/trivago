package com.trivago.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.trivago.core.models.*
import com.trivago.data.repository.CharacterDetailsRepository

class CharacterDetailsViewModel(
    private val characterDetailsRepository: CharacterDetailsRepository
) : ViewModel() {

    suspend fun fetchSpecies(characterUrl: String): SpeciesResponse =
        characterDetailsRepository.fetchSpecies(characterUrl)

    suspend fun fetchSpeciesDetails(speciesUrl: String): SpeciesDetailResponse =
        characterDetailsRepository.fetchSpeciesDetails(speciesUrl)

    suspend fun fetchFilms(characterUrl: String): FilmsResponse =
        characterDetailsRepository.fetchFilms(characterUrl)

    suspend fun fetchFilmDetails(filmsUrl: String): FilmDetailResponse =
        characterDetailsRepository.fetchFilmDetails(filmsUrl)

    suspend fun fetchPlanet(characterUrl: String): PlanetResponse =
        characterDetailsRepository.fetchPlanet(characterUrl)

    suspend fun fetchPlanetDetails(planetUrl: String): PlanetDetailsResponse =
        characterDetailsRepository.fetchPlanetDetails(planetUrl)
}