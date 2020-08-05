package com.trivago.data.repository

import com.trivago.core.data.api.StarWarsAPI
import com.trivago.core.data.mappers.toResponse
import com.trivago.core.data.models.StarWarsCharacter
import com.trivago.data.dao.CharacterDao
import com.trivago.data.model.Character
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * CharacterSearchRepository
 *
 * This repository class is responsible for handling anything related to character search
 *
 * @param starWarsAPI
 * @param characterDao
 */
class CharacterSearchRepository(
    private val starWarsAPI: StarWarsAPI,
    private val characterDao: CharacterDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    /**
     * SearchStarWarsCharacters is responsible for searching the character using the character name
     *
     * @param characterName
     * @return a flow list of the character
     */
    suspend fun searchStarWarsCharacters(characterName: String): Flow<List<StarWarsCharacter>> =
        flow {

            val characters = starWarsAPI.searchCharacters(characterName)
            val starWarsCharacters = mutableListOf<StarWarsCharacter>()
            for (starWarsCharacter in characters.results) {
                starWarsCharacters.add(starWarsCharacter.toResponse())
            }
            emit(starWarsCharacters)
        }.flowOn(ioDispatcher)

    /**
     * Responsible for saving/inserting a character into the database
     *
     * @param character
     */
    suspend fun saveCharacter(character: Character) =
        characterDao.insert(character)

    /**
     * Responsible for fetching all characters from the db - for search suggestions purposes
     *
     * @return a flow list of the characters
     */
    fun getCharacters(): Flow<List<Character>> = characterDao.getCharacters()
}