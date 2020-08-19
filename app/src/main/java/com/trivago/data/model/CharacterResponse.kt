package com.trivago.data.model

import com.trivago.core.data.models.Film
import com.trivago.core.data.models.Planet
import com.trivago.core.data.models.Species
import com.trivago.core.network.NetworkResult

data class CharacterResponse(
    val planet: NetworkResult<Planet?>?,
    val films: List<Film>?,
    val species: List<Species>?
)