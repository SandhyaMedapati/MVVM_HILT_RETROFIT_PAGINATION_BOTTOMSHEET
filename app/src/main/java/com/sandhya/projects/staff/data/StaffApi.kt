package com.sandhya.projects.staff.data

import retrofit2.Response
import retrofit2.http.GET

interface StaffApi {
    @GET("api/characters/staff")
    suspend fun getStaffData(): Response<List<StaffData>>
}

