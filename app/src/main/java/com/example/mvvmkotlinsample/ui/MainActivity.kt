package com.example.mvvmkotlinsample

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmkotlinsample.databinding.ActivityMainBinding
import com.example.mvvmkotlinsample.utils.NormalClass
import com.example.mvvmkotlinsample.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var normalClass: NormalClass
    private val mainViewModel : MainViewModel by viewModels()
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding= DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)
        var viewModel = ViewModelProvider(this@MainActivity).get(MainViewModel::class.java)

        println("newvalue : ${normalClass.xyz}")
        println("mainViewModel : ${mainViewModel.abc}")

        //this is a conflict test
       //communication between fragment
        // The nicest way is to use a shared ViewModel.
       viewModel.recieveData("Pacafista")

       viewModel.empdata().observe(this, Observer {
            Log.d(ContentValues.TAG, "News data: ${it}")
            binding.data = it
        })


        //communication between thread
        viewModel.senddata().observe(this, Observer {
            Log.d(ContentValues.TAG, "communication data: ${it}")

            CoroutineScope(Dispatchers.IO).launch {
                println("syncadata${it}")
                withContext(Dispatchers.Main) {
                //viewModel.recieveData("Pacafista")
                }}})

        CoroutineScope(Dispatchers.IO).launch {
            //do your task( this is background thread)
            Thread.sleep(5000)
            //to switch between the backgropund thread to main thread
            withContext(Dispatchers.Main) {
                viewModel.recieveData("Pacafista")
            //switching the thread to main if you want to update on ui(this is main thread)
            }}}
}
