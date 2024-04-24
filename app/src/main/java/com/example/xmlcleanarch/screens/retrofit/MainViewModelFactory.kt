package com.example.xmlcleanarch.screens.retrofit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.xmlcleanarch.repository.retrofitRepository.PostRepository

class MainViewModelFactory(private val repository: PostRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}