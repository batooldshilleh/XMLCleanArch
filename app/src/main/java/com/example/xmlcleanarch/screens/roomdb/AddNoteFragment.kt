package com.example.xmlcleanarch.screens.roomdb

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.xmlcleanarch.R
import com.example.xmlcleanarch.data.roomdata.Note
import com.example.xmlcleanarch.databinding.FragmentAddNoteBinding
import com.example.xmlcleanarch.databinding.FragmentListBinding

class AddNoteFragment : Fragment() {


private lateinit var binding: FragmentAddNoteBinding
private lateinit var noteViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddNoteBinding.inflate(inflater, container, false)

        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        binding.btnAdd.setOnClickListener {
            insertNote()
        }
        return binding.root
    }

    private fun insertNote() {
        val title = binding.etNote.text.toString()
        val description = binding.etDescription.text.toString()
        val currentTimeMillis = System.currentTimeMillis()

        if (inputCheck(title, description)) {
            val note = Note(title, description, currentTimeMillis)
            noteViewModel.insertNote(note)
            Toast.makeText(requireContext(), "Note added", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addNoteFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(title: String, description: String): Boolean{
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(description))
    }

}