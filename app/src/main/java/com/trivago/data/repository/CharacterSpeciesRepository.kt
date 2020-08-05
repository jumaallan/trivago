package com.trivago.data.repository

import com.trivago.core.data.api.StarWarsAPI
import com.trivago.core.data.mappers.toResponse
import com.trivago.core.data.models.Species
import com.trivago.core.utils.toHttps
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * CharacterSpeciesRepository
 *
 * This repository class is responsible for handling anything related to character species
 *
 * @param starWarsAPI
 */
class CharacterSpeciesRepository(
    private val starWarsAPI: StarWarsAPI,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    /**
     * FetchSpecies is responsible for the following
     *      - fetch species as a List<String>
     *      - fetch species details for each specie
     *
     * @param characterUrl
     * @return a flow list of the the species
     */
    suspend fun fetchSpecies(characterUrl: String): Flow<List<Species>> = flow {
        val speciesResponse = starWarsAPI.fetchSpecies(characterUrl.toHttps())
        val species = mutableListOf<Species>()
        for (specieUrl in speciesResponse.species) {
            val specie = starWarsAPI.fetchSpeciesDetails(specieUrl.toHttps())
            species.add(specie.toResponse())
        }
        emit(species)
    }.flowOn(ioDispatcher)
}