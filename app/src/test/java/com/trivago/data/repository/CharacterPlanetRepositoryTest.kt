package com.trivago.data.repository

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.trivago.BaseTest
import com.trivago.dispatcher.MockRequestDispatcher
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
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