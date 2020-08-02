package com.trivago.data.repository

import com.trivago.core.data.api.StarWarsAPI
import com.trivago.core.data.models.Film
import com.trivago.core.data.network.FilmDetailResponse
import com.trivago.core.utils.toHttps
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CharacterFilmsRepository(
    private val starWarsAPI: StarWarsAPI
) {

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

private fun FilmDetailResponse.toResponse(): Film =
    Film(this.title, this.openingCrawl)
