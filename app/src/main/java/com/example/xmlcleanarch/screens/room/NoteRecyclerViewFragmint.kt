package com.example.xmlcleanarch.screens.room

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.xmlcleanarch.R
import com.example.xmlcleanarch.databinding.ActivityRoomDbBinding
import com.example.xmlcleanarch.databinding.FragmentNoteRecyclerViewBinding

class NoteRecyclerViewFragmint : Fragment() {
    private lateinit var binding: FragmentNoteRecyclerViewBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoteRecyclerViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.floatingActionButton.setOnClickListener {
            val addNoteFragment = AddNoteFragment()
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.fcNote, addNoteFragment) // Corrected container ID
                addToBackStack(null)
                commit()
            }
        }
    }

}


