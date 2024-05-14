package com.example.xmlcleanarch.screens.live_data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor() : ViewModel() {
    private val _welcomeMessage = MutableLiveData<String>()
    val welcomeMessage: LiveData<String>
        get() = _welcomeMessage

    fun generateWelcomeMessage(name: String) {
        _welcomeMessage.value = "Welcome, $name!"
    }
}