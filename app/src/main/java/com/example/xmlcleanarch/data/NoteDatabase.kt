package com.example.xmlcleanarch.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.xmlcleanarch.data.roomdata.NotDao
import com.example.xmlcleanarch.data.roomdata.Note

@Database(
    entities = [Note::class],
    version = 1
)
abstract class NoteDatabase: RoomDatabase(){
    abstract val dao: NotDao
}