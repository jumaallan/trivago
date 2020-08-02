package com.trivago.data.repository

import com.google.common.truth.Truth
import com.trivago.BaseTest
import com.trivago.dispatcher.MockRequestDispatcher
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class CharacterPlanetRepositoryTest : BaseTest() {

    private lateinit var characterPlanetRepository: CharacterPlanetRepository

    @Before
    fun setUp() {
        super.setup()
        characterPlanetRepository = CharacterPlanetRepository(starWarsAPI)
    }

    @Test
    fun fetchPlanet() {
        runBlocking {
            val planet =
                characterPlanetRepository.fetchPlanet(MockRequestDispatcher.EXISTING_CHARACTER_URL)
            Truth.assertThat(planet.name).matches("Tatooine")
        }
    }
}