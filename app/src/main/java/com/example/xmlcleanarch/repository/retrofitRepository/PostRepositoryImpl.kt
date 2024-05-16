package com.example.xmlcleanarch.repository.retrofitRepository

import com.example.xmlcleanarch.api.PostService
import com.example.xmlcleanarch.data.retrofitModel.Post
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostRepositoryImpl @Inject constructor(private val postClientAPI: PostService) : PostRepositoryInterface {

    override suspend fun getPost(): Response<Post> {
        return postClientAPI.getPost()
    }

    override suspend fun getPostByNumber(number: Int): Response<Post> {
        return postClientAPI.getPostByNumber(number)
    }

    override suspend fun getCustomPost(userId: Int, sort: String, order: String): Response<List<Post>> {
        return postClientAPI.getCustomPost(userId, sort, order)
    }

    override suspend fun getCustomPostMap(userId: Int, options: Map<String, String>): Response<List<Post>> {
        return postClientAPI.getCustomPostMap(userId, options)
    }

    override suspend fun pushPost(post: Post): Response<Post> {
        return postClientAPI.pushPost(post)
    }
}