package com.example.xmlcleanarch.screens.retrofit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xmlcleanarch.models.retrofitmodel.Post
import com.example.xmlcleanarch.repository.retrofitRepository.PostRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: PostRepository): ViewModel(){

    val response: MutableLiveData<Post> = MutableLiveData()

    fun getPost() {
        viewModelScope.launch {
            val getResponse = repository.getPost()
            response.value = getResponse
        }
    }
}