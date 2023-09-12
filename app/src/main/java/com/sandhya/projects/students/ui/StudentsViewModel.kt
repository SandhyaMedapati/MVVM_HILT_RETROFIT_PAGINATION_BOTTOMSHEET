package com.sandhya.projects.students.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sandhya.projects.students.data.StudentsRepository
import com.sandhya.projects.students.data.StudentsData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentsViewModel @Inject constructor(private val studentsRepository: StudentsRepository) :
    ViewModel() {

    val studentsList = MutableLiveData<List<StudentsData>>()
    var isLoading = MutableLiveData<Boolean>().apply { value = false }
    val pageSize = 20
    private var currentChunkIndex = 0

    fun fetchInitialStudents() {
        viewModelScope.launch {
            kotlin.runCatching {
                studentsRepository.getStudentsData()
            }.onSuccess { response ->
                val initialData = response.body()?.take(pageSize) ?: emptyList()
                studentsList.postValue(initialData)
            }.onFailure {
                // Handle the failure here
                Log.e("StaffViewModel", it.message.toString())
            }
        }
    }

    fun fetchRemainingStudents() {
        isLoading.value = true
        viewModelScope.launch {
            kotlin.runCatching {
                studentsRepository.getStudentsData()
            }.onSuccess { response ->
                val allData = response.body() ?: emptyList()
                val initialData = studentsList.value.orEmpty()
                val remainingData = allData.drop(initialData.size)

                val chunkedData = remainingData.chunked(pageSize)

                if (currentChunkIndex < chunkedData.size) {
                    val updatedList = (studentsList.value ?: emptyList()).toMutableList()
                    updatedList.addAll(chunkedData[currentChunkIndex])
                    studentsList.postValue(updatedList)

                    // Increment the index for the next chunk
                    currentChunkIndex++
                }
            }.onFailure {
                studentsList.postValue(emptyList())
                Log.e("StudentsViewModel", it.message.toString())
            }
            isLoading.value = false
        }
    }
}