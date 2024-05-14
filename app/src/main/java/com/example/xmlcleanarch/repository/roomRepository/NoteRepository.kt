package com.example.xmlcleanarch.repository.roomRepository

import com.example.xmlcleanarch.data.roomdata.NoteDao
import com.example.xmlcleanarch.data.roomdata.Note
import kotlinx.coroutines.flow.Flow

class NoteRepository(private val noteDao: NoteDao) {

    val allNotByDate: Flow<List<Note>> = noteDao.getNotesOrderedByDateAdded()

    suspend fun upsertNote(note: Note) {
        noteDao.upsertNote(note)
    }

    suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }

}