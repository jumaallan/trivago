package com.trivago.ui.viewmodel

import com.jraska.livedata.test
import com.trivago.BaseViewModelTest
import com.trivago.data.repository.CharacterSearchRepository
import com.trivago.data.sample.character
import com.trivago.data.sample.characterName
import io.mockk.*
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Test

class CharacterSearchViewModelTest : BaseViewModelTest() {

    private val repo = mockk<CharacterSearchRepository>()
    private lateinit var characterSearchViewModel: CharacterSearchViewModel

    @Before
    fun setUp() {
        characterSearchViewModel = CharacterSearchViewModel(repo)
    }

    @Test
    fun `test that repo receives search character string`() {
        every { repo.searchStarWarsCharacters(any()) } returns flowOf(emptyList())
        characterSearchViewModel.searchStarWarsCharacters(characterName)
        verify { repo.searchStarWarsCharacters(characterName) }
    }

    @Test
    fun `test that repo's saveCharacter is called with right parameters`() {
        coEvery { repo.saveCharacter(any()) } just Runs
        characterSearchViewModel.saveCharacter(character)
        coVerify { repo.saveCharacter(character) }
    }

    @Test
    fun `test that characters are fetched from the repo successfully`() {
        every { repo.getCharacters() } returns flowOf(listOf(character))
        val characters = characterSearchViewModel.getCharacters()
        characters.test().assertValue(listOf(character))
    }
}