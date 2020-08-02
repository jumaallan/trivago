package com.trivago.data.repository

import com.trivago.core.data.api.StarWarsAPI
import com.trivago.core.data.models.Planet
import com.trivago.core.data.network.PlanetDetailsResponse
import com.trivago.core.utils.toHttps

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
    suspend fun fetchPlanet(characterUrl: String): Planet {
        val planetResponse = starWarsAPI.fetchPlanet(characterUrl.toHttps())
        val planet = starWarsAPI.fetchPlanetDetails(planetResponse.homeworld.toHttps())
        return planet.toResponse()
    }
}

/**
 * Responsible for mapping the PlanetDetailsResponse to Planet
 */
private fun PlanetDetailsResponse.toResponse(): Planet =
    Planet(this.name, this.population)