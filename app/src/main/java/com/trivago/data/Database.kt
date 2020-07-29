package com.trivago.data

import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.trivago.data.dao.CharacterDao
import com.trivago.data.model.Character

@androidx.room.Database(
    entities = [
        Character::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateConverter::class)
abstract class Database : RoomDatabase() {

    abstract fun characterDao(): CharacterDao
}
