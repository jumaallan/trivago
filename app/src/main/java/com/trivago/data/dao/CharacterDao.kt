package com.trivago.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.trivago.data.model.Character
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao : BaseDao<Character> {

    @Query("SELECT * FROM Character")
    fun getCharacters(): Flow<List<Character>>
}