package com.example.xmlcleanarch.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.xmlcleanarch.repository.retrofitRepository.PostRepository
import com.example.xmlcleanarch.screens.retrofit.RetrofitViewModel

@Suppress("UNCHECKED_CAST")
class RetrofitFactory(
    private val repository: PostRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RetrofitViewModel(repository) as T
    }
}