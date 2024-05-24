package com.example.xmlcleanarch.di.retrofit

import com.example.xmlcleanarch.repository.retrofitRepository.PostRepositoryImpl
import com.example.xmlcleanarch.repository.retrofitRepository.PostRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryRetrofitModule {
    @Binds
    abstract fun bindRepository(repository: PostRepositoryImpl): PostRepository
}