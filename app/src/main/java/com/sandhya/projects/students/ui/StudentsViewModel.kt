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
class StudentsViewModel @Inject constructor(private val studentsRepository: StudentsRepository) : ViewModel() {

    val studentsList = MutableLiveData<List<StudentsData>>()

    fun fetchAllStaff() {
        viewModelScope.launch {
            kotlin.runCatching {
                studentsRepository.getStudentsData()
            }.onSuccess {
                studentsList.postValue(it.body())
            }.onFailure {
                // Handle the failure here
                Log.e("StudentsViewModel", it.message.toString())
            }
        }
    }
}