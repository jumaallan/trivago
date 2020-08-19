package com.trivago.data.sample

import com.trivago.core.data.models.Film
import com.trivago.core.data.models.Planet
import com.trivago.core.data.models.Species
import com.trivago.core.network.NetworkResult
import com.trivago.data.model.Character

const val characterUrl = "http://test"
const val characterName = "some character"

val character = Character("name", "2020", "2", "http://test")

val testCharacters = listOf(
    Character("Darth Vader", "", "", ""),
    Character("Yoda", "", "", ""),
    Character("Biggs Dark", "", "", "")
)

val film = listOf(
    Film("test", "test")
)

val planetResponse = Planet("test", "1000")

val planet: NetworkResult<Planet> =
    NetworkResult.Success(planetResponse)

val species = listOf(
    Species("test", "english")
)