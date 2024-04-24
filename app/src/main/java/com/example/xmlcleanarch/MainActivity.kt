package com.example.xmlcleanarch

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.xmlcleanarch.databinding.ActivityMainBinding

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
            navigate("com.example.xmlcleanarch.screens.live_data.LiveDataActivity")
        }
        binding.btnRoom.setOnClickListener {
            navigate("com.example.xmlcleanarch.screens.roomdb.roomActivity")
        }
        binding.btnRetrofit.setOnClickListener {
            navigate("com.example.xmlcleanarch.screens.retrofit.RetrofitPostActivity")
        }
    }
    fun navigate(className: String) {

        val intent = Intent(this@MainActivity, Class.forName(className))
        startActivity(intent)

    }
}