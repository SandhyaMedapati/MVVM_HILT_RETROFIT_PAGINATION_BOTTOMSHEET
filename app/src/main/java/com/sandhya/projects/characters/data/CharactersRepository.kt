package com.sandhya.projects.characters.data

import retrofit2.Response

interface CharactersRepository {
    suspend fun getCharactersData() : Response<List<CharactersData>>
}