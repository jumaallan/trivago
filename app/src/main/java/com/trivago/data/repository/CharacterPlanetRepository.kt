package com.trivago.data.repository

import com.trivago.core.data.api.StarWarsAPI
import com.trivago.core.data.mappers.toResponse
import com.trivago.core.data.models.Planet
import com.trivago.core.network.NetworkResult
import com.trivago.core.network.safeApiCall
import com.trivago.core.utils.toHttps
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * CharacterPlanetRepository
 *
 * This repository class is responsible for handling anything related to character planet
 *
 * @param starWarsAPI
 */
class CharacterPlanetRepository(
    private val starWarsAPI: StarWarsAPI,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    /**
     * FetchPlanets is responsible for the following
     *      - fetch planet url
     *      - fetch planet details using the planet url
     *
     * @param characterUrl
     * @return the planet details
     */
    suspend fun fetchPlanet(characterUrl: String): NetworkResult<Planet> =
        safeApiCall(ioDispatcher) {
            val planetResponse = starWarsAPI.fetchPlanet(characterUrl.toHttps())
            val planet = starWarsAPI.fetchPlanetDetails(planetResponse.homeworld.toHttps())
            return@safeApiCall planet.toResponse()
        }

//        return try {
//            val planetResponse = starWarsAPI.fetchPlanet(characterUrl.toHttps())
//            val planet = starWarsAPI.fetchPlanetDetails(planetResponse.homeworld.toHttps())
//            planet.toResponse()
//        } catch (t: Throwable) {
//            Timber.e(t, "Fetch Planet API call failed. Character URL: $characterUrl")
//            null
//        }
//    }
}