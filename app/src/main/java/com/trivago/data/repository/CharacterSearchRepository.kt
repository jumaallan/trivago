package com.trivago.data.repository

import com.trivago.core.api.StarWarsAPI
import com.trivago.core.models.CharacterResponse
import com.trivago.data.dao.CharacterDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CharacterSearchRepository(
    private val starWarsAPI: StarWarsAPI,
    private val characterDao: CharacterDao
) {

    suspend fun searchStarWarsCharacters(characterName: String): Flow<List<CharacterResponse>> =
        flow {
            val characters = starWarsAPI.searchCharacters(characterName)
            val starWarsCharacters = mutableListOf<CharacterResponse>()
            starWarsCharacters.addAll(characters.results)
            emit(starWarsCharacters)
        }
}