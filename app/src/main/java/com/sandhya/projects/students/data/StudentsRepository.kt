package com.sandhya.projects.students.data

import retrofit2.Response

interface StudentsRepository {
    suspend fun getStudentsData(): Response<List<StudentsData>>
}