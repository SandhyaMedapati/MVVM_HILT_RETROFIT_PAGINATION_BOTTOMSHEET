package com.sandhya.projects.characters.module

import com.sandhya.projects.characters.data.CharactersRepository
import com.sandhya.projects.characters.data.CharactersRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface CharactersRepositoriesModule {

    @Binds
    fun charactersRepository(charactersRepositoryImpl: CharactersRepositoryImpl): CharactersRepository
}