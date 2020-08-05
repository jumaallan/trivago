package com.trivago.ui.viewmodel

import com.jraska.livedata.test
import com.trivago.BaseViewModelTest
import com.trivago.core.data.models.Film
import com.trivago.core.data.models.Planet
import com.trivago.core.data.models.Species
import com.trivago.data.model.CharacterResponse
import com.trivago.data.repository.CharacterFilmsRepository
import com.trivago.data.repository.CharacterPlanetRepository
import com.trivago.data.repository.CharacterSpeciesRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Test

class CharacterDetailsViewModelTest : BaseViewModelTest() {

    private val characterFilmsRepository = mockk<CharacterFilmsRepository>()
    private val characterPlanetRepository = mockk<CharacterPlanetRepository>()
    private val characterSpeciesRepository = mockk<CharacterSpeciesRepository>()
    lateinit var characterDetailsViewModel: CharacterDetailsViewModel

    @Before
    fun setUp() {
        characterDetailsViewModel = CharacterDetailsViewModel(
            characterSpeciesRepository,
            characterFilmsRepository,
            characterPlanetRepository
        )
    }

    @Test
    fun `test get character details are fetched successfully`() {
        coEvery { characterFilmsRepository.fetchFilms(any()) } returns flowOf(
            listOf(
                Film(
                    "test",
                    "test"
                )
            )
        )
        coEvery { characterPlanetRepository.fetchPlanet(any()) } returns Planet("test", "1000")
        coEvery { characterSpeciesRepository.fetchSpecies(any()) } returns flowOf(
            listOf(
                Species(
                    "test",
                    "english"
                )
            )
        )
        characterDetailsViewModel.getCharacterDetails("http://test")
        coVerify { characterFilmsRepository.fetchFilms("http://test") }
        coVerify { characterPlanetRepository.fetchPlanet("http://test") }
        coVerify { characterSpeciesRepository.fetchSpecies("http://test") }

        characterDetailsViewModel.characterResponseState.test().assertValue(
            CharacterResponse(
                Planet("test", "1000"),
                listOf(Film("test", "test")),
                listOf(Species("test", "english"))
            )
        )
    }

}