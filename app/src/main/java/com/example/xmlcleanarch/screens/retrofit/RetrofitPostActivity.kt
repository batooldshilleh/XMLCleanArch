package com.example.xmlcleanarch.screens.retrofit

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.xmlcleanarch.R
import com.example.xmlcleanarch.databinding.ActivityLiveDataBinding
import com.example.xmlcleanarch.databinding.ActivityRetrofitPostBinding
import com.example.xmlcleanarch.repository.retrofitRepository.PostRepository

class RetrofitPostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRetrofitPostBinding
    private lateinit var postViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRetrofitPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val postRepository = PostRepository()
        val viewModelFactory = MainViewModelFactory(postRepository)
        postViewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)
        postViewModel.getPost()
        postViewModel.response.observe(this, Observer { response ->
            Log.d("Response", response.userId.toString())
            Log.d("Response", response.id.toString())
            Log.d("Response", response.title)
            Log.d("Response", response.body)
        })
        }

}