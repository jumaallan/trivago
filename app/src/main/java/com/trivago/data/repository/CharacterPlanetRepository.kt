package com.trivago.data.repository

import com.trivago.core.data.api.StarWarsAPI
import com.trivago.core.data.mappers.toResponse
import com.trivago.core.data.models.Planet
import com.trivago.core.utils.toHttps
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * CharacterPlanetRepository
 *
 * This repository class is responsible for handling anything related to character planet
 *
 * @param starWarsAPI
 */
class CharacterPlanetRepository(
    private val starWarsAPI: StarWarsAPI
) {

    /**
     * FetchPlanets is responsible for the following
     *      - fetch planet url
     *      - fetch planet details using the planet url
     *
     * @param characterUrl
     * @return the planet details
     */
    suspend fun fetchPlanet(characterUrl: String): Planet = withContext(Dispatchers.IO) {
        val planetResponse = starWarsAPI.fetchPlanet(characterUrl.toHttps())
        val planet = starWarsAPI.fetchPlanetDetails(planetResponse.homeworld.toHttps())
        return@withContext planet.toResponse()
    }
}