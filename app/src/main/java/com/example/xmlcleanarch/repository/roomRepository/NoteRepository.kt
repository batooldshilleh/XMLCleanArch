package com.example.xmlcleanarch.repository.roomRepository

import androidx.lifecycle.LiveData
import com.example.xmlcleanarch.data.roomdata.NotDao
import com.example.xmlcleanarch.data.roomdata.Note
import kotlinx.coroutines.flow.Flow

class NoteRepository(private val noteDao: NotDao) {

    val getAllNoatByDate: LiveData<List<Note>> = noteDao.getNotesOrderdByDateAdded()

    val getAllNoatByAddres: LiveData<List<Note>> = noteDao.getNotesOrderdByDateAdded()

    suspend fun upsertNote(note: Note){
        noteDao.upsertNote(note)
    }

}