package com.trivago.ui.viewmodel

import com.jraska.livedata.test
import com.trivago.BaseViewModelTest
import com.trivago.data.model.Character
import com.trivago.data.repository.CharacterSearchRepository
import io.mockk.*
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Test


class CharacterSearchViewModelTest : BaseViewModelTest() {

    private val repo = mockk<CharacterSearchRepository>()
    lateinit var characterSearchViewModel: CharacterSearchViewModel

    @Before
    fun setUp() {
        characterSearchViewModel = CharacterSearchViewModel(repo)
    }

    @Test
    fun `test that repo receives search character string`() {
        every { repo.searchStarWarsCharacters(any()) } returns flowOf(emptyList())
        characterSearchViewModel.searchStarWarsCharacters("some character")
        verify { repo.searchStarWarsCharacters("some character") }
    }

    @Test
    fun `test that repo's saveCHaracter is called with right parameters`() {
        val character = Character("name", "2020", "2", "http://test")
        coEvery { repo.saveCharacter(any()) } just Runs
        characterSearchViewModel.saveCharacter(character)
        coVerify { repo.saveCharacter(character) }
    }

    @Test
    fun `test that characters are fetched from the repo successfully`() {
        val character = Character("name", "2020", "2", "http://test")
        every { repo.getCharacters() } returns flowOf(listOf(character))
        val characters = characterSearchViewModel.getCharacters()
        characters.test().assertValue(listOf(character))
    }
}