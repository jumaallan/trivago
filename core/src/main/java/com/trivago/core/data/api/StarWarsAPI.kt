package com.trivago.core.data.api

import com.trivago.core.data.network.*
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * StarWars API
 *
 * This is the retrofit interface to handle the various calls to the star wars api. This file defines the various
 * methods, the requests and responses from the various APIs
 */
interface StarWarsAPI {

    @GET("people/")
    suspend fun searchCharacters(@Query("search") params: String): SearchResponse

    @GET
    suspend fun fetchSpecies(@Url characterUrl: String): SpeciesResponse

    @GET
    suspend fun fetchSpeciesDetails(@Url speciesUrl: String): SpeciesDetailResponse

    @GET
    suspend fun fetchFilms(@Url characterUrl: String): FilmsResponse

    @GET
    suspend fun fetchFilmDetails(@Url filmsUrl: String): FilmDetailResponse

    @GET
    suspend fun fetchPlanet(@Url characterUrl: String): PlanetResponse

    @GET
    suspend fun fetchPlanetDetails(@Url planetUrl: String): PlanetDetailsResponse
}