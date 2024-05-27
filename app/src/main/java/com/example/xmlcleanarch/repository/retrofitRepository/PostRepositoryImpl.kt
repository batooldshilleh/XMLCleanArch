package com.example.xmlcleanarch.repository.retrofitRepository

import com.example.xmlcleanarch.api.PostService
import com.example.xmlcleanarch.data.retrofitModel.Post
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostRepositoryImpl @Inject constructor(
    private val simplePostService: PostService
) : PostRepository {

    override suspend fun getPost(): Response<Post> {
        return simplePostService.getPost()
    }

    override suspend fun getPostByNumber(number: Int): Response<Post> {
        return simplePostService.getPostByNumber(number)
    }

    override suspend fun getCustomPost(
        userId: Int,
        sort: String,
        order: String
    ): Response<List<Post>> {
        return simplePostService.getCustomPost(userId, sort, order)
    }

    override suspend fun getCustomPostMap(
        userId: Int,
        options: Map<String, String>
    ): Response<List<Post>> {
        return simplePostService.getCustomPostMap(userId, options)
    }

    override suspend fun pushPost(post: Post): Response<Post> {
        return simplePostService.pushPost(post)
    }
}