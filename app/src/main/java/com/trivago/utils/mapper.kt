package com.trivago.utils

import com.trivago.core.data.models.StarWarsCharacter
import com.trivago.data.model.Character

/**
 * Responsible for mapping the Character to StarWarsCharacter
 */
fun Character.toResponse(): StarWarsCharacter =
    StarWarsCharacter(this.name, this.birthYear, this.height, this.url)


/**
 * Responsible for mapping the StarWarsCharacter to Character
 */
fun StarWarsCharacter.toResponse(): Character =
    Character(this.name, this.birthYear, this.height, this.url)

