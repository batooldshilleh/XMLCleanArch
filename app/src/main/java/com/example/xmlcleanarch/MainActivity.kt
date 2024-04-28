package com.example.xmlcleanarch

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.xmlcleanarch.databinding.ActivityMainBinding
import com.example.xmlcleanarch.screens.live_data.LiveDataActivity
import com.example.xmlcleanarch.screens.roomdb.RoomActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupListeners()
    }

    private fun setupListeners() {
        binding.btnLiveData.setOnClickListener {
            navigateToActivity(LiveDataActivity::class.java)
        }
        binding.btnRoom.setOnClickListener {
            startActivity(RoomActivity.newIntent(this))
        }
    }

    private fun navigateToActivity(activityClass: Class<*>) {
        try {
            val intent = Intent(this@MainActivity, activityClass)
            startActivity(intent)
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }
    }
}