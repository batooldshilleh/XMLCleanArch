package com.example.xmlcleanarch.di.database

import android.content.Context
import androidx.room.Room
import com.example.xmlcleanarch.data.roomdata.NoteDao
import com.example.xmlcleanarch.data.roomdata.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext
        applicationContext: Context):
            NoteDatabase {
        return Room.databaseBuilder(
            applicationContext,
            NoteDatabase::class.java,
            "note_database"
        ).build()
    }

    @Provides
    fun provideNotDao(noteDatabase:NoteDatabase): NoteDao {
        return noteDatabase.noteDao
    }
}