package com.trivago.core.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StarWarsCharacter(
    val name: String,
    val birthYear: String,
    val height: String,
    val url: String
) : Parcelable