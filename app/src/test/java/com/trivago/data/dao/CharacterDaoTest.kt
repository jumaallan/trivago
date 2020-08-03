package com.trivago.data.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.trivago.BaseTest
import com.trivago.data.model.Character
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
internal class CharacterDaoTest : BaseTest() {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Test
    fun `test inserting and retrieving characters`() = runBlockingTest {
        val testCharacters = listOf(
            Character("Darth Vader", "", "", ""),
            Character("Yoda", "", "", ""),
            Character("Biggs Dark", "", "", "")
        )
        characterDao.insert(testCharacters)
        val character = characterDao.getCharacters().first().toList()[0]
        assertThat(character.name, `is`(testCharacters[0].name))
    }
}