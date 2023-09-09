package com.sandhya.projects.students.module

import com.sandhya.projects.students.data.StudentsRepository
import com.sandhya.projects.students.data.StudentsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface StudentsRepositoriesModule {

    @Binds
    fun studentsRepository(studentsRepositoryImpl: StudentsRepositoryImpl): StudentsRepository
}