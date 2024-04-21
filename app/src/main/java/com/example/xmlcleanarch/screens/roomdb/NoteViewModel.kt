package com.example.xmlcleanarch.screens.roomdb


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xmlcleanarch.data.roomdata.NotDao
import com.example.xmlcleanarch.data.roomdata.Note
import com.example.xmlcleanarch.data.roomdata.NoteDatabase
import com.example.xmlcleanarch.repository.roomRepository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class NoteViewModel(application: Application): AndroidViewModel(application) {

    private val getAllNoteByDate: Flow<List<Note>>
    private val repository: NoteRepository

    init {
        val dao = NoteDatabase.getDatabase(application).dao
        repository = NoteRepository(dao)
        getAllNoteByDate = repository.getAllNoatByDate
    }

    fun insertNote(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            repository.upsertNote(note)
        }
    }
}