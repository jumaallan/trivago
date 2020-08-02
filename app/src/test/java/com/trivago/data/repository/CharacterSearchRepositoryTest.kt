package com.trivago.data.repository

import com.google.common.truth.Truth
import com.trivago.BaseTest
import com.trivago.dispatcher.MockRequestDispatcher
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class CharacterSearchRepositoryTest : BaseTest() {

    private lateinit var characterSearchRepository: CharacterSearchRepository

    @Before
    fun setUp() {
        super.setup()
        characterSearchRepository = CharacterSearchRepository(starWarsAPI, characterDao)
    }

    @InternalCoroutinesApi
    @Test
    fun searchStarWarsCharacters() {
        runBlocking {
            val characterResponse = characterSearchRepository.searchStarWarsCharacters(
                MockRequestDispatcher.EXISTING_CHARACTER_URL
            )
            characterResponse.collect { Truth.assertThat(it).isNotEmpty() }
        }
    }

    @Test
    fun saveCharacter() {
    }

    @Test
    fun getCharacters() {
    }
}