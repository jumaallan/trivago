package com.trivago.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Character(
    @PrimaryKey
    var id: Long,
    var name: String,
    var birthYear: String,
    var height: String,
    var url: String
)