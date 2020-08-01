package com.trivago.data.repository

import com.trivago.core.data.api.StarWarsAPI
import com.trivago.core.data.models.StarWarsCharacter
import com.trivago.core.data.network.CharacterResponse
import com.trivago.data.dao.CharacterDao
import com.trivago.data.model.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CharacterSearchRepository(
    private val starWarsAPI: StarWarsAPI,
    private val characterDao: CharacterDao
) {

    suspend fun searchStarWarsCharacters(characterName: String): Flow<List<StarWarsCharacter>> =
        flow {
            val characters = starWarsAPI.searchCharacters(characterName)
            val starWarsCharacters = mutableListOf<StarWarsCharacter>()
            for (starWarsCharacter in characters.results) {
                starWarsCharacters.add(starWarsCharacter.toResponse())
            }
            emit(starWarsCharacters)
        }

    suspend fun saveCharacter(character: Character) =
        characterDao.insert(character)

    fun getCharacters(): Flow<List<Character>> =
        characterDao.getCharacters()
}

private fun CharacterResponse.toResponse(): StarWarsCharacter {
    return StarWarsCharacter(this.name, this.birthYear, this.height, this.url)
}
