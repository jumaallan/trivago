package com.trivago.core.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StarWarsCharacter(
    val name: String,
    val birthYear: String,
    val heightCM: String,
    val heightInches: String,
    val url: String
) : Parcelable