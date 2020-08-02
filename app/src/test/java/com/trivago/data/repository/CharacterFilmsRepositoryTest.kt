package com.trivago.data.repository

import com.google.common.truth.Truth
import com.trivago.BaseTest
import com.trivago.dispatcher.MockRequestDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class CharacterFilmsRepositoryTest : BaseTest() {

    private lateinit var characterFilmsRepository: CharacterFilmsRepository

    @Before
    fun setUp() {
        super.setup()
        characterFilmsRepository = CharacterFilmsRepository(starWarsAPI)
    }

    @Test
    fun fetchFilms() {
        runBlocking {
            val filmsResponse =
                characterFilmsRepository.fetchFilms(MockRequestDispatcher.EXISTING_CHARACTER_URL)
            filmsResponse.collect { films ->
                Truth.assertThat(films.size).isAtLeast(1)
            }
        }
    }
}