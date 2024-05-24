package com.example.xmlcleanarch.repository.retrofitRepository

import com.example.xmlcleanarch.data.retrofitModel.Post
import retrofit2.Response

interface PostRepository {

    suspend fun getPost(): Response<Post>

    suspend fun getPostByNumber(number: Int): Response<Post>

    suspend fun getCustomPost(userId: Int, sort: String, order: String): Response<List<Post>>

    suspend fun getCustomPostMap(userId: Int, options: Map<String, String>): Response<List<Post>>

    suspend fun pushPost(post: Post): Response<Post>
}