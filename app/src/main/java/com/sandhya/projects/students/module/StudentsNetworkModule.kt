package com.sandhya.projects.students.module

import com.sandhya.projects.NetworkModule.provideApiClient
import com.sandhya.projects.students.data.StudentsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object StudentsNetworkModule {

    @Provides
    fun provideStudentsApi(retrofit: Retrofit): StudentsApi {
        return provideApiClient(retrofit)
    }
}
