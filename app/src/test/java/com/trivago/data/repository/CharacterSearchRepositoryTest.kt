package com.trivago.data.repository

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.trivago.BaseTest
import com.trivago.dispatcher.MockRequestDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class CharacterSearchRepositoryTest : BaseTest() {

    private lateinit var characterSearchRepository: CharacterSearchRepository

    @Before
    fun setUp() {
        super.setup()
        characterSearchRepository = CharacterSearchRepository(starWarsAPI, characterDao)
    }

    @Test
    fun `search for a character that exists and get results`() {
        runBlocking {
            val characterResponse = characterSearchRepository.searchStarWarsCharacters(
                MockRequestDispatcher.EXISTING_SEARCH_PARAMS
            )
            characterResponse.collect { Truth.assertThat(it).isNotEmpty() }
        }
    }

    @Test
    fun `search for a character that doesn't exist and get no results`() {
        runBlocking {
            val characterResponse = characterSearchRepository.searchStarWarsCharacters(
                MockRequestDispatcher.NON_EXISTENT_SEARCH_PARAMS
            )
            characterResponse.collect { Truth.assertThat(it).isEmpty() }
        }
    }

    @Test
    fun saveCharacter() {
    }

    @Test
    fun getCharacters() {
    }
}