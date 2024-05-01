package com.example.xmlcleanarch

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.xmlcleanarch.databinding.ActivityMainBinding
import com.example.xmlcleanarch.screens.live_data.LiveDataActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupListener()
    }

    fun setupListener() {
        binding.btnLiveData.setOnClickListener {
            startActivity(LiveDataActivity.newIntent(this))
        }
    }

}