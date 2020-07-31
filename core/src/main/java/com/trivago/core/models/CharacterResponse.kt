package com.trivago.core.models

import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    val name: String,
    @SerializedName("birth_year")
    val birthYear: String,
    val height: String,
    val url: String
)