package com.example.xmlcleanarch.factory


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.xmlcleanarch.data.roomdata.NotDao
import com.example.xmlcleanarch.screens.roomdb.NoteViewModel

class NoteViewModelFactory(private val dao: NotDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NoteViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
