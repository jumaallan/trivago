package com.trivago.data

import com.trivago.core.data.models.Film
import com.trivago.core.data.models.Planet
import com.trivago.core.data.models.Species
import com.trivago.core.data.models.StarWarsCharacter
import com.trivago.core.network.NetworkResult
import com.trivago.data.model.Character

val starWarsCharacter = StarWarsCharacter(
    "Test",
    "2020",
    "160",
    "20",
    "http://some-url"
)

val starWarsCharacterTwo = StarWarsCharacter("name2", "2020", "2", "1", "http://test2")

val character = Character("name", "2020", "2", "http://test")

val planet = Planet(
    "Tatooine",
    "120000"
)

val dummyPlanet: NetworkResult<Planet> = NetworkResult.Success(planet)

val dummyFilms = listOf(
    Film(
        "A new Hope",
        "It is a period of civil war.\\n\\nRebel spaceships, striking\\n\\nfrom a hidden base, have won\\n\\ntheir first victory against\\n\\nthe evil Galactic Empire.\\n\\n\\n\\nDuring the battle, Rebel\\n\\nspies managed to steal secret\\r\\nplans to the Empire's\\n\\nultimate weapon, the DEATH\\n\\nSTAR, an armored space\\n\\nstation with enough power\\n\\nto destroy an entire planet.\\n\\n\\n\\nPursued by the Empire's\\n\\nsinister agents, Princess\\n\\nLeia races home aboard her\\n\\nstarship, custodian of the\\n\\nstolen plans that can save her\\n\\npeople and restore\\n\\nfreedom to the galaxy...."
    )
)

val dummySpecies = listOf(
    Species(
        "Wookie",
        "Shyriiwook"
    )
)