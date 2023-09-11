package com.sandhya.projects.staff.data

import javax.inject.Inject

class StaffRepositoryImpl @Inject constructor(private val staffApi: StaffApi) : StaffRepository {
    override suspend fun getStaffData() = staffApi.getStaffData()
}