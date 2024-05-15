package com.example.xmlcleanarch.repository.roomRepository

import com.example.xmlcleanarch.data.roomdata.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository{

    suspend fun upsertNote(note: Note)

    suspend fun deleteNote(note: Note)

    suspend fun getAllNotes(): Flow<List<Note>>

}