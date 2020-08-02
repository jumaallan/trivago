package com.trivago.data.repository

import com.trivago.core.data.api.StarWarsAPI
import com.trivago.core.data.models.Planet
import com.trivago.core.data.network.PlanetDetailsResponse
import com.trivago.core.utils.toHttps

class CharacterPlanetRepository(
    private val starWarsAPI: StarWarsAPI
) {

    suspend fun fetchPlanet(characterUrl: String): Planet {
        val planetResponse = starWarsAPI.fetchPlanet(characterUrl.toHttps())
        val planet = starWarsAPI.fetchPlanetDetails(planetResponse.homeworld.toHttps())
        return planet.toResponse()
    }
}

private fun PlanetDetailsResponse.toResponse(): Planet =
    Planet(this.name, this.population)