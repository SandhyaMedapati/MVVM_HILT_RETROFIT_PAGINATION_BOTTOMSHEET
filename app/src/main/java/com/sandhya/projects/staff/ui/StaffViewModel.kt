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
    var isLoading = MutableLiveData<Boolean>().apply { value = false }
    val pageSize = 20
    private var currentChunkIndex = 0

    fun fetchInitialStaff() {
        viewModelScope.launch {
            kotlin.runCatching {
                staffRepository.getStaffData()
            }.onSuccess { response ->
                val initialData = response.body()?.take(pageSize) ?: emptyList()
                staffList.postValue(initialData)
            }.onFailure {
                // Handle the failure here
                Log.e("StaffViewModel", it.message.toString())
            }
        }
    }

    fun fetchRemainingStaff() {
        isLoading.value = true
        viewModelScope.launch {
            kotlin.runCatching {
                staffRepository.getStaffData()
            }.onSuccess { response ->
                val allData = response.body() ?: emptyList()
                val initialData = staffList.value.orEmpty()
                val remainingData = allData.drop(initialData.size)

                val chunkedData = remainingData.chunked(pageSize)

                if (currentChunkIndex < chunkedData.size) {
                    val updatedList = (staffList.value ?: emptyList()).toMutableList()
                    updatedList.addAll(chunkedData[currentChunkIndex])
                    staffList.postValue(updatedList)

                    // Increment the index for the next chunk
                    currentChunkIndex++
                }
            }.onFailure {
                staffList.postValue(emptyList())
                Log.e("StaffViewModel", it.message.toString())
            }
            isLoading.value = false
        }
    }
}