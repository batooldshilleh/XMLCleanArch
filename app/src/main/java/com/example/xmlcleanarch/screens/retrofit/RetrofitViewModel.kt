package com.example.xmlcleanarch.screens.retrofit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xmlcleanarch.data.retrofitModel.Post
import com.example.xmlcleanarch.repository.retrofitRepository.PostRepositoryImpl
import com.example.xmlcleanarch.util.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class RetrofitViewModel @Inject constructor(private val repository: PostRepositoryImpl) :
    ViewModel() {

    val apiResponsePost: MutableLiveData<Response<Post>> = MutableLiveData()
    val status: MutableLiveData<String> = MutableLiveData()
    val errorMessage: MutableLiveData<String> = MutableLiveData()
    val posts: MutableLiveData<List<Post>> = MutableLiveData()
    val validationStatus: MutableLiveData<String> = MutableLiveData()

    fun validateAndGetCustomPost(userId: String) {
        if (userId.isEmpty()) {
            validationStatus.value = "User ID must be filled"
            return
        }
        val userNumberInt = userId.toIntOrNull()
        if (userNumberInt == null) {
            validationStatus.value = "Please enter a valid number"
            return
        }
        getCustomPost(userNumberInt)
    }

    private fun getCustomPost(userId: Int) {
        status.value = Status.LOADING.toString()
        viewModelScope.launch {
            try {
                val response: Response<List<Post>> = repository.getCustomPost(userId, "id", "desc")
                if (response.isSuccessful) {
                    status.value = Status.SUCCESS.toString()
                    posts.value = response.body()
                } else {
                    status.value = Status.ERROR.toString()
                    errorMessage.value = "Error: ${response.code()}"
                }
            } catch (e: Exception) {
                status.value = Status.ERROR.toString()
                errorMessage.value = "Error: ${e.message}"
            }
        }
    }

    fun validateAndPushPost(userId: String, id: String, title: String, body: String) {
        if (userId.isEmpty() || id.isEmpty() || title.isEmpty() || body.isEmpty()) {
            validationStatus.value = "All fields must be filled"
            return
        }
        val userNumberInt = userId.toIntOrNull()
        val idInt = id.toIntOrNull()

        if (userNumberInt == null || idInt == null) {
            validationStatus.value = "Please enter valid numbers"
            return
        }
        pushPost(userNumberInt, idInt, title, body)
    }

    private fun pushPost(userNumber: Int, id: Int, title: String, body: String) {
        val post = Post(userNumber, id, title, body)
        status.value = Status.LOADING.toString()
        viewModelScope.launch {
            try {
                val response: Response<Post> = repository.pushPost(post)
                if (response.isSuccessful) {
                    status.value = Status.SUCCESS.toString()
                    apiResponsePost.value = response
                } else {
                    status.value = Status.ERROR.toString()
                    errorMessage.value = "Error: ${response.code()}"
                }
            } catch (e: Exception) {
                status.value = Status.ERROR.toString()
                errorMessage.value = "Error: ${e.message}"
            }
        }
    }

}