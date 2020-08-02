package com.trivago.data.repository

import com.trivago.core.data.api.StarWarsAPI
import com.trivago.core.data.models.Film
import com.trivago.core.data.network.FilmDetailResponse
import com.trivago.core.utils.toHttps
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * CharacterFilmsRepository
 *
 * This class is responsible for handling anything related to character films
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
     * @return a flowable list of the the film
     */
    suspend fun fetchFilms(characterUrl: String): Flow<List<Film>> = flow {
        val filmsResponse = starWarsAPI.fetchFilms(characterUrl.toHttps())
        val films = mutableListOf<Film>()
        for (filmUrl in filmsResponse.films) {
            val film = starWarsAPI.fetchFilmDetails(filmUrl.toHttps())
            films.add(film.toResponse())
        }
        emit(films)
    }
}

/**
 * Responsible for mapping the FilmDetailResponse to Film
 */
private fun FilmDetailResponse.toResponse(): Film =
    Film(this.title, this.openingCrawl)