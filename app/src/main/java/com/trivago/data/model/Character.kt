package com.trivago.data.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["name"], unique = true)])
// @Fts3 // FTS3 and FTS4 are SQLite virtual table modules that allows users to perform full-text searches on a set of documents - https://www.sqlite.org/fts3.html || https://developer.android.com/reference/androidx/room/Fts3
// Use `@Fts3` only if your app has strict disk space requirements or if you
// require compatibility with an older SQLite version.
data class Character(
    @PrimaryKey
//    @Ignore
//    @ColumnInfo(name = "rowid")
    var name: String,
    var birthYear: String,
    var height: String,
    var url: String
)