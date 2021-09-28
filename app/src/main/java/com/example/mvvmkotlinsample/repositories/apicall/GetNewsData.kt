package com.example.mvvmkotlinsample.repositories.apicall

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmkotlinsample.models.NewsData
import com.globalnews.repositories.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class GetNewsData {

    var getdata = MutableLiveData<NewsData>();

    fun getresponse(): LiveData<NewsData>
    {
        val mutableMap: MutableMap<String, String> = mutableMapOf<String, String>()
        mutableMap.put("name", "Ashu")
        mutableMap.put("city", "Delhi")

        val call: Call<NewsData>? = ApiClient.getClient.getNews(mutableMap)
        call!!.enqueue(object : Callback<NewsData?> {
            @RequiresApi(api = Build.VERSION_CODES.O)
            override fun onResponse(call: Call<NewsData?>, response: Response<NewsData?>) {

                if (response.isSuccessful()) {
                    getdata.value = response.body()
                    println("result loginhit = ${response.body()}")
                }}
            override fun onFailure(call: Call<NewsData?>, t: Throwable) {
                call.cancel()
            }})
        return getdata
    }
}