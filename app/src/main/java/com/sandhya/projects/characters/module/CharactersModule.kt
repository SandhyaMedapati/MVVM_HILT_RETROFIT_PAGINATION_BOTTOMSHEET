package com.sandhya.projects.characters.module

import com.sandhya.projects.NetworkModule
import com.sandhya.projects.characters.data.CharactersApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object CharactersModule {

    @Provides
    fun provideCharactersApi(retrofit: Retrofit): CharactersApi {
        return NetworkModule.provideApiClient(retrofit)
    }
}