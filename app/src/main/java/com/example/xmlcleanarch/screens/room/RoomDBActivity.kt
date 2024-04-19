package com.example.xmlcleanarch.screens.room

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.xmlcleanarch.R

import com.example.xmlcleanarch.databinding.ActivityRoomDbBinding

class RoomDBActivity : AppCompatActivity() {
    private lateinit var bindingRecyclerView: ActivityRoomDbBinding
    val recyclerViewFragmint = NoteRecyclerViewFragmint()
    val addNoteFragment = AddNoteFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingRecyclerView = ActivityRoomDbBinding.inflate(layoutInflater)
        setContentView(bindingRecyclerView.root)

        if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction().apply {
                add(R.id.fcNote, recyclerViewFragmint)
                commit()
            }
        }




    }

    fun defaultFragment() {

        val fcRecycler = bindingRecyclerView.fcNote
        supportFragmentManager.beginTransaction().apply {
            add(fcRecycler.id,recyclerViewFragmint)
            commit()
        }
    }



}