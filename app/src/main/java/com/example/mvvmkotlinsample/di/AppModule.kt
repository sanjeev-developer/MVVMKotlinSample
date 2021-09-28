package com.example.mvvmkotlinsample.di

import com.example.mvvmkotlinsample.utils.NetworkConstants
import com.example.mvvmkotlinsample.utils.NormalClass
import com.globalnews.repositories.network.ApiClient
import com.globalnews.repositories.network.ApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule
{
    @Singleton
    @Provides
    fun getBaseUrl(): String = NetworkConstants.BASE_URL

    @Singleton
    @Provides
    fun getAPiinterface(): ApiInterface =  ApiClient.getClient

    @Singleton
    @Provides
    fun getRetrofit(baseUrl: String): Retrofit = Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build()

    @Provides
    fun getNormalObj() : NormalClass {
        return NormalClass()
    }

//    @Singleton
//    @Provides
//    fun getPostRequest(retrofit: Retrofit): PostRequest = retrofit.create(PostRequest::class.java)
}