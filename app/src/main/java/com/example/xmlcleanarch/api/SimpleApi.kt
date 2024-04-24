package com.example.xmlcleanarch.api

import com.example.xmlcleanarch.models.retrofitmodel.Post
import retrofit2.http.GET

interface SimpleApi {

    @GET("posts/1")
    suspend fun getPost(): Post
}