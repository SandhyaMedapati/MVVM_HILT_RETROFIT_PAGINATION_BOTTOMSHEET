package com.sandhya.projects.characters.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sandhya.projects.characters.data.CharactersData
import com.sandhya.projects.characters.data.CharactersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(private val charactersRepository: CharactersRepository) :
    ViewModel() {

    val charactersList = MutableLiveData<List<CharactersData>>()
    var isLoading = MutableLiveData<Boolean>().apply { value = false }
    val pageSize = 20
    private var currentChunkIndex = 0

    fun fetchInitialCharacters() {
        viewModelScope.launch {
            kotlin.runCatching {
                charactersRepository.getCharactersData()
            }.onSuccess { response ->
                val initialData = response.body()?.take(pageSize) ?: emptyList()
                charactersList.postValue(initialData)
            }.onFailure {
                // Handle the failure here
                Log.e("StaffViewModel", it.message.toString())
            }
        }
    }

    fun fetchRemainingCharacters() {
        isLoading.value = true
        viewModelScope.launch {
            kotlin.runCatching {
                charactersRepository.getCharactersData()
            }.onSuccess { response ->
                val allData = response.body() ?: emptyList()
                val initialData = charactersList.value.orEmpty()
                val remainingData = allData.drop(initialData.size)

                val chunkedData = remainingData.chunked(pageSize)

                if (currentChunkIndex < chunkedData.size) {
                    val updatedList = (charactersList.value ?: emptyList()).toMutableList()
                    updatedList.addAll(chunkedData[currentChunkIndex])
                    charactersList.postValue(updatedList)

                    // Increment the index for the next chunk
                    currentChunkIndex++
                }
            }.onFailure {
                charactersList.postValue(emptyList())
                Log.e("StudentsViewModel", it.message.toString())
            }
            isLoading.value = false
        }
    }
}