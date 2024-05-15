package com.example.xmlcleanarch.screens.roomdb

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xmlcleanarch.data.roomdata.Note
import com.example.xmlcleanarch.repository.roomRepository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NoteViewModel @Inject constructor(val repository: NoteRepository) : ViewModel() {

    private val _allNoteByDate = MutableLiveData<List<Note>>()
    val allNoteByDate: LiveData<List<Note>> = _allNoteByDate

   /* val data: Flow<List<Note>>
            = combine( ) {
       repository.getAllNotes()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList<Note>()
    )*/

    public fun getAllNotes() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllNotes().collect { notes ->
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
