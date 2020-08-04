package com.trivago.data.repository

import com.trivago.core.data.api.StarWarsAPI
import com.trivago.core.data.mappers.toResponse
import com.trivago.core.data.models.Film
import com.trivago.core.utils.toHttps
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

/**
 * CharacterFilmsRepository
 *
 * This repository class is responsible for handling anything related to character films
 *
 * @param starWarsAPI
 */
class CharacterFilmsRepository(
    private val starWarsAPI: StarWarsAPI
) {

    /**
     * FetchFilms is responsible for the following
     *      - fetch films as a List<String>
     *      - fetch film details for each film
     *
     * @param characterUrl
     * @return a flow list of the the film
     */
    suspend fun fetchFilms(characterUrl: String): Flow<List<Film>> = withContext(Dispatchers.IO) {
        flow {
            val filmsResponse = starWarsAPI.fetchFilms(characterUrl.toHttps())
            val films = mutableListOf<Film>()
            for (filmUrl in filmsResponse.films) {
                val film = starWarsAPI.fetchFilmDetails(filmUrl.toHttps())
                films.add(film.toResponse())
            }
            emit(films)
        }
    }
}