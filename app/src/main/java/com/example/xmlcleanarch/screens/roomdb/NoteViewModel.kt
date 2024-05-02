package com.example.xmlcleanarch.screens.roomdb


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xmlcleanarch.data.roomdata.NoteDao
import com.example.xmlcleanarch.data.roomdata.Note
import com.example.xmlcleanarch.repository.roomRepository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class NoteViewModel(dao: NoteDao) : ViewModel() {

    val allNoteByDate: Flow<List<Note>>
    private val repository: NoteRepository

    init {

        repository = NoteRepository(dao)
        allNoteByDate = repository.allNotByDate
    }

    fun insertNote(title: String, description: String, dateAdd: Long) {
        val note = Note(title, description, dateAdd)
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