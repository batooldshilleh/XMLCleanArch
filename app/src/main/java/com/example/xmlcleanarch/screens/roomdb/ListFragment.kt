package com.example.xmlcleanarch.screens.roomdb

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import com.example.xmlcleanarch.R
import com.example.xmlcleanarch.adapters.roomAdapter.NoteAdapter
import com.example.xmlcleanarch.databinding.FragmentListBinding

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.xmlcleanarch.data.roomdata.Note


class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private lateinit var noteViewModel: NoteViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)


        val adapter = NoteAdapter()

        val recyclerView = binding.rvNote
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        noteViewModel.getAllNoteByDate.observe(viewLifecycleOwner, Observer { note ->
            adapter.setData(note)
        })

        adapter.setOnDeleteClickListener { note ->
            noteViewModel.deleteNote(note)
        }

        noteViewModel.allNotes.observe(viewLifecycleOwner, Observer { notes ->
            adapter.setData(notes)
        })



        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addNoteFragment)
        }



        return binding.root
    }

}