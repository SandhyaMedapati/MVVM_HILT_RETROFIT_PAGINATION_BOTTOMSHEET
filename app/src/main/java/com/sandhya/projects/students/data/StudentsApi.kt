package com.sandhya.projects.students.data

import retrofit2.Response
import retrofit2.http.GET

interface StudentsApi {
    @GET("api/characters/students")
    suspend fun getStudentData(): Response<List<StudentsData>>
}