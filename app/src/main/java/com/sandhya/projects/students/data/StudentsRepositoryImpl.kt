package com.sandhya.projects.students.data

import javax.inject.Inject

class StudentsRepositoryImpl @Inject constructor(private val studentsApi: StudentsApi) :
    StudentsRepository {
    override suspend fun getStudentsData() = studentsApi.getStudentData()
}