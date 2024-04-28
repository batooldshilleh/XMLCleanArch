package com.example.xmlcleanarch.screens.roomdb


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xmlcleanarch.data.roomdata.NotDao
import com.example.xmlcleanarch.data.roomdata.Note
import com.example.xmlcleanarch.repository.roomRepository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class NoteViewModel(dao: NotDao) : ViewModel() {

    val getAllNoteByDate: Flow<List<Note>>
    private val repository: NoteRepository

    init {

        repository = NoteRepository(dao)
        getAllNoteByDate = repository.getAllNotByDate
    }

    fun insertNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.upsertNote(note)
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteNote(note)
        }
    }


}