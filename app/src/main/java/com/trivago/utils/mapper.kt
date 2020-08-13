package com.trivago.utils

import com.trivago.core.data.models.StarWarsCharacter
import com.trivago.core.utils.toInches
import com.trivago.data.model.Character

/**
 * Responsible for mapping the Character to StarWarsCharacter
 */
fun Character.toResponse(): StarWarsCharacter =
    StarWarsCharacter(
        name = this.name,
        birthYear = this.birthYear,
        heightCM = this.height,
        heightInches = this.height.toInches(),
        url = this.url
    )

/**
 * Responsible for mapping the StarWarsCharacter to Character
 */
fun StarWarsCharacter.toResponse(): Character =
    Character(name = this.name, birthYear = this.birthYear, height = this.heightCM, url = this.url)