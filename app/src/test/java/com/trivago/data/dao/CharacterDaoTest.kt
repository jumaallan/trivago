package com.trivago.data.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.trivago.BaseTest
import com.trivago.data.model.Character
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
internal class CharacterDaoTest : BaseTest() {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Test
    fun `test inserting and retrieving characters`() = runBlockingTest {
        val testCharacters = listOf(Character(1, "Darth Vader", "", "", ""), Character(2, "Yoda", "", "", ""), Character(3, "Biggs Dark", "", "", ""))
        characterDao.insert(testCharacters)
        val character = characterDao.getCharacters().first().toList()[0]
        assertThat(character.rowId, `is`(testCharacters[0].rowId))
    }
}