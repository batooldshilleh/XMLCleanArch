package com.example.xmlcleanarch.screens.live_data

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.xmlcleanarch.databinding.ActivityLiveDataBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LiveDataActivity : AppCompatActivity() {
    private val viewModel: WelcomeViewModel by viewModels()
    private lateinit var binding: ActivityLiveDataBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLiveDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnShowMessage.setOnClickListener {
            val name = binding.etNameEnter.text.toString()
            viewModel.generateWelcomeMessage(name)
        }

        viewModel.welcomeMessage.observe(this) { message ->
            binding.tvMessage.text = message
        }
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, LiveDataActivity::class.java)
        }
    }
}