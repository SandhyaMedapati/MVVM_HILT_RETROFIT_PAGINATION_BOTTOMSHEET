package com.sandhya.projects.staff.module

import com.sandhya.projects.NetworkModule.provideApiClient
import com.sandhya.projects.staff.data.StaffApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object StaffModule {

    @Provides
    fun provideStaffApi(retrofit: Retrofit): StaffApi {
        return provideApiClient(retrofit)
    }
}
