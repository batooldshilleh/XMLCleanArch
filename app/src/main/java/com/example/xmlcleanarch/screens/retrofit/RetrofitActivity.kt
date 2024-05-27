package com.example.xmlcleanarch.screens.retrofit

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.xmlcleanarch.R
import com.example.xmlcleanarch.databinding.ActivityRetrofitBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RetrofitActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRetrofitBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRetrofitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarRetrofit)
        setupActionBarWithNavController(findNavController(R.id.fargmentRetrofit))
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fargmentRetrofit)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, RetrofitActivity::class.java)
        }
    }
}