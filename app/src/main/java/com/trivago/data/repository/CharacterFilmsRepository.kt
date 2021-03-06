package com.trivago.data.repository

import com.trivago.core.data.api.StarWarsAPI
import com.trivago.core.data.mappers.toResponse
import com.trivago.core.data.models.Film
import com.trivago.core.utils.toHttps
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber

/**
 * CharacterFilmsRepository
 *
 * This repository class is responsible for handling anything related to character films
 *
 * @param starWarsAPI
 */
class CharacterFilmsRepository(
    private val starWarsAPI: StarWarsAPI,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    /**
     * FetchFilms is responsible for the following
     *      - fetch films as a List<String>
     *      - fetch film details for each film
     *
     * @param characterUrl
     * @return a flow list of the the film
     */
    suspend fun fetchFilms(characterUrl: String): Flow<List<Film>> = flow {
        val filmsResponse = starWarsAPI.fetchFilms(characterUrl.toHttps())
        val films = mutableListOf<Film>()
        for (filmUrl in filmsResponse.films) {
            val film = starWarsAPI.fetchFilmDetails(filmUrl.toHttps())
            films.add(film.toResponse())
        }
        emit(films)
    }.catch { e ->
        Timber.e(e, "Fetch Films API call failed. Character URL: $characterUrl")
    }.flowOn(ioDispatcher)
}