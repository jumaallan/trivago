package com.trivago.data.repository

import com.google.common.truth.Truth
import com.trivago.BaseTest
import com.trivago.dispatcher.MockRequestDispatcher
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class CharacterSpeciesRepositoryTest : BaseTest() {

    private lateinit var characterSpeciesRepository: CharacterSpeciesRepository

    @Before
    fun setUp() {
        super.setup()
        characterSpeciesRepository = CharacterSpeciesRepository(starWarsAPI)
    }

    @InternalCoroutinesApi
    @Test
    fun fetchSpecies() {
        runBlocking {
            val speciesResponse =
                characterSpeciesRepository.fetchSpecies(MockRequestDispatcher.EXISTING_CHARACTER_URL)
            speciesResponse.collect { species ->
                Truth.assertThat(species.size).isAtLeast(1)
            }
        }
    }
}