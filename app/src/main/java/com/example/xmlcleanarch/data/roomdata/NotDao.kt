package com.example.xmlcleanarch.data.roomdata

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert


@Dao
interface NotDao {

    @Upsert
    suspend fun upsertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM note ORDER BY dateAdd")
    fun getNotesOrderdByDateAdded(): LiveData<List<Note>>


    @Query("SELECT * FROM note ORDER BY title ASC")
    fun getNoteOrderdByTitle(): LiveData<List<Note>>
}