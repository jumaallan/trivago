package com.trivago.data

import com.trivago.core.data.models.Film
import com.trivago.core.data.models.Planet
import com.trivago.core.data.models.Species

val dummyPlanet = Planet(
    "Tatooine",
    "120000"
)

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