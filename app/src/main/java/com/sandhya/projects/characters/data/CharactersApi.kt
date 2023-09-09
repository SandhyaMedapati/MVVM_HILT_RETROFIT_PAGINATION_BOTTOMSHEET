package com.sandhya.projects.characters.data

import retrofit2.Response
import retrofit2.http.GET

interface CharactersApi {
    @GET("api/characters/")
    suspend fun getCharactersData(): Response<List<CharactersData>>
}