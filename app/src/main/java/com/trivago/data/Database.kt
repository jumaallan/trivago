package com.trivago.data

import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@androidx.room.Database(
    entities = [

    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateConverter::class)
abstract class Database : RoomDatabase()
