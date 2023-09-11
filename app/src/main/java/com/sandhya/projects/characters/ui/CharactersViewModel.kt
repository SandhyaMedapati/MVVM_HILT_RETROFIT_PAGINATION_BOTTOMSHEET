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

    fun fetchAllCharacters() {
        viewModelScope.launch {
            kotlin.runCatching {
                charactersRepository.getCharactersData()
            }.onSuccess {
                charactersList.postValue(it.body())
            }.onFailure {
                // Handle the failure here
                Log.e("CharactersViewModel", it.message.toString())
            }
        }
    }
}