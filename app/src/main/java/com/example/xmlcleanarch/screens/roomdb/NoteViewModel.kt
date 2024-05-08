package com.example.xmlcleanarch.screens.roomdb


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xmlcleanarch.data.roomdata.NoteDao
import com.example.xmlcleanarch.data.roomdata.Note
import com.example.xmlcleanarch.repository.roomRepository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class NoteViewModel(dao: NoteDao) : ViewModel() {

    private val _allNoteByDate = MutableLiveData<List<Note>>()
    val allNoteByDate: LiveData<List<Note>> = _allNoteByDate

    private val repository: NoteRepository

    init {
        repository = NoteRepository(dao)
        getAllNotes()
    }

    private fun getAllNotes() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.allNotByDate.collect { notes ->
                _allNoteByDate.postValue(notes)
            }
        }
    }
    fun insertNote(title: String, description: String) {
        val currentTimeMillis = System.currentTimeMillis()
        val note = Note(title, description, currentTimeMillis)
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
