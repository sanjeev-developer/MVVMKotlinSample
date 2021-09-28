package com.example.mvvmkotlinsample.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmkotlinsample.models.NewsData
import com.example.mvvmkotlinsample.repositories.apicall.GetNewsData
import com.example.mvvmkotlinsample.utils.NormalClass
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(var normalClass: NormalClass) : ViewModel()
{
    var abc:String = "sanjeev"
    var newData = MutableLiveData<String>()

    fun empdata(): LiveData<NewsData> {
        println("modeldata ${normalClass.xyz}")
        return GetNewsData().getresponse()
    }

    fun senddata(): LiveData<String>
    {
        return newData
    }

    fun recieveData(s: String)
    {
        newData.value = s
    }
}