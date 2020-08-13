package com.trivago.core.data.mappers

import com.trivago.core.data.models.Film
import com.trivago.core.data.models.Planet
import com.trivago.core.data.models.Species
import com.trivago.core.data.models.StarWarsCharacter
import com.trivago.core.data.network.CharacterResponse
import com.trivago.core.data.network.FilmDetailResponse
import com.trivago.core.data.network.PlanetDetailsResponse
import com.trivago.core.data.network.SpeciesDetailResponse
import com.trivago.core.utils.convertToInches

/**
 * Responsible for mapping the FilmDetailResponse to Film
 */
fun FilmDetailResponse.toResponse(): Film =
    Film(this.title, this.openingCrawl)

/**
 * Responsible for mapping the PlanetDetailsResponse to Planet
 */
fun PlanetDetailsResponse.toResponse(): Planet =
    Planet(this.name, this.population)

/**
 * Responsible for mapping the CharacterResponse to StarWarsCharacter
 */
fun CharacterResponse.toResponse(): StarWarsCharacter =
    StarWarsCharacter(
        this.name,
        this.birthYear,
        this.height,
        convertToInches(this.height),
        this.url
    )

/**
 * Responsible for mapping the SpeciesDetailResponse to Species
 */
fun SpeciesDetailResponse.toResponse(): Species =
    Species(this.name, this.language)