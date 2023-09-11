package com.sandhya.projects.staff.data

import retrofit2.Response

interface StaffRepository {
    suspend fun getStaffData(): Response<List<StaffData>>
}

