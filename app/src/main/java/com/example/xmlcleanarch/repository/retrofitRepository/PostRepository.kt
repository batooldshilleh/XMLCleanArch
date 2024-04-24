package com.example.xmlcleanarch.repository.retrofitRepository

import com.example.xmlcleanarch.api.RetrofitInstance
import com.example.xmlcleanarch.models.retrofitmodel.Post

class PostRepository {

    suspend fun getPost(): Post {
       return RetrofitInstance.api.getPost()
    }
}