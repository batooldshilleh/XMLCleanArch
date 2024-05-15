package com.example.xmlcleanarch.repository.roomRepository

import com.example.xmlcleanarch.data.roomdata.Note
import com.example.xmlcleanarch.data.roomdata.NoteDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteRepositoryImpl @Inject constructor(private val noteDao: NoteDao): NoteRepository {

    override suspend fun upsertNote(note: Note) = noteDao.upsertNote(note)
    override suspend fun deleteNote(note: Note) = noteDao.deleteNote(note)
    override suspend fun getAllNotes(): Flow<List<Note>> = noteDao.getNotesOrderedByDateAdded()
}