package com.trivago.core.models

import com.google.gson.annotations.SerializedName

data class FilmDetailResponse(
    val title: String,
    @SerializedName("opening_crawl")
    val openingCrawl: String
)