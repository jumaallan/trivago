package com.trivago.data.repository

import com.trivago.core.api.StarWarsAPI
import com.trivago.data.dao.CharacterDao

class CharacterDetailsRepository(
    private val starWarsAPI: StarWarsAPI,
    private val characterDao: CharacterDao
)