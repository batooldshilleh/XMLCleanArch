package com.example.xmlcleanarch.screens.retrofit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xmlcleanarch.data.retrofitModel.Post
import com.example.xmlcleanarch.repository.retrofitRepository.PostRepository
import kotlinx.coroutines.launch
import retrofit2.Response


class RetrofitViewModel(private val repository: PostRepository) : ViewModel() {

    private val apiResponse: MutableLiveData<Response<Post>> = MutableLiveData()
    private val apiResponseGet: MutableLiveData<Response<Post>> = MutableLiveData()
    val apiResponseGetCustom: MutableLiveData<Response<List<Post>>> = MutableLiveData()
    private val apiResponseGetCustomMap: MutableLiveData<Response<List<Post>>> = MutableLiveData()
    val apiResponsePost: MutableLiveData<Response<Post>> = MutableLiveData()
    fun getPost() {
        viewModelScope.launch {
            val response: Response<Post> = repository.getPost()
            apiResponse.value = response
        }
    }

    fun getPostByNumber(number: Int) {
        viewModelScope.launch {
            val response: Response<Post> = repository.getPostByNumber(number)
            apiResponseGet.value = response
        }
    }

    fun getCustomPost(userId: Int) {
        viewModelScope.launch {
            val response: Response<List<Post>> = repository.getCustomPost(userId, "id", "desc")
            apiResponseGetCustom.value = response
        }
    }

    fun getCustomPostMap(userId: Int, options: Map<String, String>) {
        viewModelScope.launch {
            val response: Response<List<Post>> = repository.getCustomPostMap(userId, options)
            apiResponseGetCustomMap.value = response
        }
    }

    fun pushPost(userNumberInt: Int, idInt: Int, title: String, body: String) {
        val post = Post(userNumberInt, idInt, title, body)
        viewModelScope.launch {
            val response: Response<Post> = repository.pushPost(post)
            apiResponsePost.value = response
        }
    }

}