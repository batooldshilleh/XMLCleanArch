package com.example.xmlcleanarch.data.roomdata

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    val title: String,
    val description: String,
    val dateAdd: Long,


    @PrimaryKey(autoGenerate = true)
    val id: Int = 0

)