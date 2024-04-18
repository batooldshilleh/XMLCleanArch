package com.example.xmlcleanarch.screens.live_data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WelcomeViewModel : ViewModel() {
    private val _welcomeMessage = MutableLiveData<String>()
    val welcomeMessage: LiveData<String>
        get() = _welcomeMessage

    fun generateWelcomeMessage(name: String) {
        _welcomeMessage.value = "Welcome, $name!"
    }
}