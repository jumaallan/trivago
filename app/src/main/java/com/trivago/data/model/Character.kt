package com.trivago.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
//@Fts3 // FTS3 and FTS4 are SQLite virtual table modules that allows users to perform full-text searches on a set of documents - https://www.sqlite.org/fts3.html || https://developer.android.com/reference/androidx/room/Fts3
data class Character(
    @PrimaryKey
//    @Ignore
//    @ColumnInfo(name = "rowid")
    var rowId: Int,
    var name: String,
    var birthYear: String,
    var height: String,
    var url: String
)