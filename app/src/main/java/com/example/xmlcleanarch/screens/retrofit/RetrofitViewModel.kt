package com.example.xmlcleanarch.screens.retrofit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xmlcleanarch.data.retrofitModel.Post
import com.example.xmlcleanarch.repository.retrofitRepository.PostRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class RetrofitViewModel @Inject constructor(private val repository: PostRepositoryImpl) : ViewModel() {

    val apiResponsePost: MutableLiveData<Response<Post>> = MutableLiveData()
    val status: MutableLiveData<String> = MutableLiveData()
    val posts: MutableLiveData<List<Post>> = MutableLiveData()

    fun getCustomPost(userId: Int) {
        status.value = "loading"
        viewModelScope.launch {
            try {
                val response: Response<List<Post>> = repository.getCustomPost(userId, "id", "desc")
                if (response.isSuccessful) {
                    status.value = "success"
                    posts.value = response.body()
                } else {
                    status.value = "error: ${response.code()}"
                }
            } catch (e: Exception) {
                status.value = "error: ${e.message}"
            }
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