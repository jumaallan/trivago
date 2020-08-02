package com.trivago.data.repository

import com.trivago.core.data.api.StarWarsAPI
import com.trivago.core.data.models.Species
import com.trivago.core.data.network.SpeciesDetailResponse
import com.trivago.core.utils.toHttps
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CharacterSpeciesRepository(
    private val starWarsAPI: StarWarsAPI
) {

    suspend fun fetchSpecies(characterUrl: String): Flow<List<Species>> = flow {
        val speciesResponse = starWarsAPI.fetchSpecies(characterUrl.toHttps())
        val species = mutableListOf<Species>()
        for (specieUrl in speciesResponse.species) {
            val specie = starWarsAPI.fetchSpeciesDetails(specieUrl.toHttps())
            species.add(specie.toResponse())
        }
        emit(species)
    }

}

private fun SpeciesDetailResponse.toResponse(): Species =
    Species(this.name, this.language)