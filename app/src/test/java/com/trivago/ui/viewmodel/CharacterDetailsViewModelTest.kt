package com.trivago.ui.viewmodel

import com.jraska.livedata.test
import com.trivago.BaseViewModelTest
import com.trivago.data.model.CharacterResponse
import com.trivago.data.repository.CharacterFilmsRepository
import com.trivago.data.repository.CharacterPlanetRepository
import com.trivago.data.repository.CharacterSpeciesRepository
import com.trivago.data.sample.characterUrl
import com.trivago.data.sample.film
import com.trivago.data.sample.planet
import com.trivago.data.sample.species
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Test

class CharacterDetailsViewModelTest : BaseViewModelTest() {

    private val characterFilmsRepository = mockk<CharacterFilmsRepository>()
    private val characterPlanetRepository = mockk<CharacterPlanetRepository>()
    private val characterSpeciesRepository = mockk<CharacterSpeciesRepository>()
    private lateinit var characterDetailsViewModel: CharacterDetailsViewModel

    @Before
    fun setUp() {
        characterDetailsViewModel = CharacterDetailsViewModel(
            characterSpeciesRepository,
            characterFilmsRepository,
            characterPlanetRepository
        )
    }

    @FlowPreview
    @Test
    fun `test get character details are fetched successfully`() {
        coEvery { characterFilmsRepository.fetchFilms(any()) } returns flowOf(film)
        coEvery { characterPlanetRepository.fetchPlanet(any()) } returns planet
        coEvery { characterSpeciesRepository.fetchSpecies(any()) } returns flowOf(species)

        characterDetailsViewModel.getCharacterDetails(characterUrl)
        coVerify { characterFilmsRepository.fetchFilms(characterUrl) }
        coVerify { characterPlanetRepository.fetchPlanet(characterUrl) }
        coVerify { characterSpeciesRepository.fetchSpecies(characterUrl) }

        characterDetailsViewModel.characterResponseState.test().assertValue(
            CharacterResponse(
                planet,
                film,
                species
            )
        )
    }
}