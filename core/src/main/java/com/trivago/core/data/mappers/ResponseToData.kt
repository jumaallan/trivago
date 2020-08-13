package com.trivago.core.data.mappers

import com.trivago.core.data.models.Film
import com.trivago.core.data.models.Planet
import com.trivago.core.data.models.Species
import com.trivago.core.data.models.StarWarsCharacter
import com.trivago.core.data.network.CharacterResponse
import com.trivago.core.data.network.FilmDetailResponse
import com.trivago.core.data.network.PlanetDetailsResponse
import com.trivago.core.data.network.SpeciesDetailResponse
import com.trivago.core.utils.toInches

/**
 * Responsible for mapping the FilmDetailResponse to Film
 */
fun FilmDetailResponse.toResponse(): Film =
    Film(title = this.title, openingCrawl = this.openingCrawl)

/**
 * Responsible for mapping the PlanetDetailsResponse to Planet
 */
fun PlanetDetailsResponse.toResponse(): Planet =
    Planet(name = this.name, population = this.population)

/**
 * Responsible for mapping the CharacterResponse to StarWarsCharacter
 */
fun CharacterResponse.toResponse(): StarWarsCharacter =
    StarWarsCharacter(
        name = this.name,
        birthYear = this.birthYear,
        heightCM = this.height,
        heightInches = this.height.toInches(),
        url = this.url
    )

/**
 * Responsible for mapping the SpeciesDetailResponse to Species
 */
fun SpeciesDetailResponse.toResponse(): Species =
    Species(name = this.name, language = this.language)