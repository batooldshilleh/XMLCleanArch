package com.example.xmlcleanarch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.xmlcleanarch.databinding.ActivityMainBinding
import com.example.xmlcleanarch.screens.live_data.LiveDataActivity
import com.example.xmlcleanarch.screens.retrofit.RetrofitActivity
import com.example.xmlcleanarch.screens.roomdb.RoomActivity


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupListener()
    }

    private fun setupListener() {
        binding.btnLiveData.setOnClickListener {
            startActivity(LiveDataActivity.newIntent(this))
        }
        binding.btnRoom.setOnClickListener {
            startActivity(RoomActivity.newIntent(this))
        }
        binding.btnRetrofit.setOnClickListener {
            startActivity(RetrofitActivity.newIntent(this))
        }
    }
}