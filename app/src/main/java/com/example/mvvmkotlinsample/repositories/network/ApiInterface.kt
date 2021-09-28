package com.globalnews.repositories.network

import com.example.mvvmkotlinsample.models.NewsData
import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.GET

interface ApiInterface {

    //simp[le get api
    @GET("everything?q=tesla&from=2021-08-05&sortBy=publishedAt&apiKey=99b8488e244e49d0b4167e2cae543afc")
    fun fetchdata(): Call<NewsData>?

    // Post method
    @FormUrlEncoded
    @POST("login")
    fun userlogin(
        @Field("email") email:String,
        @Field("password") password:String): Call<NewsData>

    // get api by object parameter
    @GET("/news")
    fun getNews(@QueryMap objData:Map<String,String>): Call<NewsData>?

    //get api with query parameter
    @GET("/news")
    fun getdata( @Query("page")  page:Int,
                 @Query("order") order: String,
                 @Query("author") author:String ): Call<NewsData>?


    //get api by appending the parameter into url
    //OR - encoded is default 'true'
    @GET("/user/{name}")
    fun getUserByName(@Path(value = "name", encoded = true) name: String?): Call<NewsData>?

    //get api by appending the parameter into url
    @GET("/user/{name}")
    fun getByOtherName(@Path(value = "name") name: String?): Call<NewsData>?

}