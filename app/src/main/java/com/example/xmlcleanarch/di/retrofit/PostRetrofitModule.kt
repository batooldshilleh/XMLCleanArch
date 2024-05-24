package com.example.xmlcleanarch.di.retrofit

import com.example.xmlcleanarch.api.PostService
import com.example.xmlcleanarch.util.retrofitConstants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OkHttpWithoutInterceptor

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OkHttpWithInterceptor

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RetrofitWithInterceptor

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RetrofitWithoutInterceptor


@Module
@InstallIn(SingletonComponent::class)
object PostRetrofitModule {

    @OkHttpWithInterceptor
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @OkHttpWithoutInterceptor
    @Provides
    @Singleton
    fun provideOkHttpClientWithouInterceptor(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }

    @RetrofitWithInterceptor
    @Provides
    @Singleton
    fun provideRetrofitWithOkHttp(@OkHttpWithInterceptor client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @RetrofitWithoutInterceptor
    @Provides
    @Singleton
    fun provideSimpleRetrofit(@OkHttpWithoutInterceptor client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideSimplePostService(@RetrofitWithoutInterceptor retrofit: Retrofit): PostService {
        return retrofit.create(PostService::class.java)
    }
}
