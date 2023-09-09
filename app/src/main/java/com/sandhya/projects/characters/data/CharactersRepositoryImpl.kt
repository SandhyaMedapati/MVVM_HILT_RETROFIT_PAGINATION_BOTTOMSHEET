package com.sandhya.projects.characters.data

import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(private val charactersApi: CharactersApi): CharactersRepository {
    override suspend fun getCharactersData() = charactersApi.getCharactersData()
}