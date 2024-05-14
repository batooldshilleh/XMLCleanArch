package com.example.xmlcleanarch.adapters.roomAdapter

import com.example.xmlcleanarch.data.roomdata.Note

interface NoteDeleteListener {
    fun onDeleteClick(note: Note)
}