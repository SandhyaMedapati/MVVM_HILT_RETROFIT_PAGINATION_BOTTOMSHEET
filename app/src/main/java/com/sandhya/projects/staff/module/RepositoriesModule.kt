package com.sandhya.projects.staff.module

import com.sandhya.projects.staff.data.StaffRepository
import com.sandhya.projects.staff.data.StaffRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoriesModule {

    @Binds
    fun staffRepository(staffRepositoryImpl: StaffRepositoryImpl): StaffRepository
}