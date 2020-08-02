package com.trivago.data.repository

import com.trivago.core.data.api.StarWarsAPI
import com.trivago.core.data.models.Planet
import com.trivago.core.data.network.PlanetDetailsResponse
import com.trivago.core.utils.toHttps
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CharacterPlanetRepository(
    private val starWarsAPI: StarWarsAPI
) {

    suspend fun fetchPlanet(characterUrl: String): Flow<Planet> = flow {
        val planetResponse = starWarsAPI.fetchPlanet(characterUrl.toHttps())
        val planet = starWarsAPI.fetchPlanetDetails(planetResponse.homeworld.toHttps())
        emit(planet.toResponse())
    }
}

private fun PlanetDetailsResponse.toResponse(): Planet =
    Planet(this.name, this.population)