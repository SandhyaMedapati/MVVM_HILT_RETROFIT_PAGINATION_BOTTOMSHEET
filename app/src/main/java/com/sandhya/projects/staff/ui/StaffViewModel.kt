package com.sandhya.projects.staff.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.lifecycle.MutableLiveData
import com.sandhya.projects.staff.data.StaffData
import com.sandhya.projects.staff.data.StaffRepository

@HiltViewModel
class StaffViewModel @Inject constructor(private val staffRepository: StaffRepository) :
    ViewModel() {

    val staffList = MutableLiveData<List<StaffData>>()

    fun fetchAllStaff() {
        viewModelScope.launch {
            kotlin.runCatching {
                staffRepository.getStaffData()
            }.onSuccess {
                staffList.postValue(it.body())
            }.onFailure {
                // Handle the failure here
                Log.e("StaffViewModel", it.message.toString())
            }
        }
    }
}
