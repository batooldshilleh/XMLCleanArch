package com.example.xmlcleanarch.repository.retrofitRepository

import com.example.xmlcleanarch.api.RetrofitInstance
import com.example.xmlcleanarch.data.retrofitModel.Post
import retrofit2.Response

class PostRepository {

    suspend fun getPost(): Response<Post> {
        return RetrofitInstance.api.getPost()
    }

    suspend fun getPostByNumber(number: Int): Response<Post> {
        return RetrofitInstance.api.getPostByNumber(number)
    }

    suspend fun getCustomPost(userId: Int, sort: String, order: String): Response<List<Post>> {
        return RetrofitInstance.api.getCustomPost(userId, sort, order)
    }

    suspend fun getCustomPostMap(userId: Int, options: Map<String, String>): Response<List<Post>> {
        return RetrofitInstance.api.getCustomPostMap(userId, options)
    }

    suspend fun pushPost(post: Post): Response<Post> {
        return RetrofitInstance.api.pushPost(post)
    }
}