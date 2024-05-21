package com.example.xmlcleanarch.di.database

import com.example.xmlcleanarch.repository.roomRepository.NoteRepository
import com.example.xmlcleanarch.repository.roomRepository.NoteRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class NoteRepositoryModule {
    @Binds
    abstract fun bindNoteRepository(repositoryImpl: NoteRepositoryImpl): NoteRepository
}