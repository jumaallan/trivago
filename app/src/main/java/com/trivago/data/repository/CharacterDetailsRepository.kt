package com.trivago.data.repository

import com.trivago.core.data.api.StarWarsAPI
import com.trivago.core.data.models.Film
import com.trivago.core.data.models.Planet
import com.trivago.core.data.models.Species
import com.trivago.core.data.network.FilmDetailResponse
import com.trivago.core.data.network.PlanetDetailsResponse
import com.trivago.core.data.network.SpeciesDetailResponse
import com.trivago.core.utils.toHttps
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CharacterDetailsRepository(
    private val starWarsAPI: StarWarsAPI
) {

    suspend fun fetchSpecies(characterUrl: String): Flow<List<Species>> = flow {
        val speciesResponse = starWarsAPI.fetchSpecies(characterUrl.toHttps())
        val species = mutableListOf<Species>()
        for (specieUrl in speciesResponse.species) {
            val specie = starWarsAPI.fetchSpeciesDetails(specieUrl.toHttps())
            species.add(specie.toResponse())
        }
        emit(species)
    }

    suspend fun fetchFilms(characterUrl: String): Flow<List<Film>> = flow {
        val filmsResponse = starWarsAPI.fetchFilms(characterUrl.toHttps())
        val films = mutableListOf<Film>()
        for (filmUrl in filmsResponse.films) {
            val film = starWarsAPI.fetchFilmDetails(filmUrl.toHttps())
            films.add(film.toResponse())
        }
        emit(films)
    }


    suspend fun fetchPlanet(characterUrl: String): Flow<Planet> = flow {
        val planetResponse = starWarsAPI.fetchPlanet(characterUrl.toHttps())
        val planet = starWarsAPI.fetchPlanetDetails(planetResponse.homeworld.toHttps())
        emit(planet.toResponse())
    }

}

private fun SpeciesDetailResponse.toResponse(): Species {
    return Species(this.name, this.language)
}

private fun FilmDetailResponse.toResponse(): Film {
    return Film(this.title, this.openingCrawl)
}

private fun PlanetDetailsResponse.toResponse(): Planet {
    return Planet(this.name, this.population)
}