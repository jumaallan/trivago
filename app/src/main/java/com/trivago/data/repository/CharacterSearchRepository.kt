package com.trivago.data.repository

import com.trivago.core.data.api.StarWarsAPI
import com.trivago.core.data.models.CharacterResponse
import com.trivago.data.dao.CharacterDao
import com.trivago.data.model.Character
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

    suspend fun saveCharacter(character: Character) =
        characterDao.insert(character)

    fun getCharacters(): Flow<List<Character>> =
        characterDao.getCharacters()
}