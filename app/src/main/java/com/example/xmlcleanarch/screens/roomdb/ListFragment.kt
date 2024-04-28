package com.example.xmlcleanarch.screens.roomdb

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.xmlcleanarch.R
import com.example.xmlcleanarch.adapters.roomAdapter.NoteAdapter
import com.example.xmlcleanarch.data.roomdata.NotDao
import com.example.xmlcleanarch.data.roomdata.NoteDatabase
import com.example.xmlcleanarch.databinding.FragmentListBinding
import com.example.xmlcleanarch.factory.NoteViewModelFactory
import com.example.xmlcleanarch.repository.roomRepository.NoteRepository
import kotlinx.coroutines.launch


class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var adapter: NoteAdapter
    private lateinit var noteDao: NotDao
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)

        setupRecyclerView()
        setupViewModel()
        setupFloatingActionButton()
        observeNoteChanges()

        return binding.root
    }


    private fun setupRecyclerView() {
        adapter = NoteAdapter { note ->
            noteViewModel.deleteNote(note)
        }
        binding.rvNote.adapter = adapter
        binding.rvNote.layoutManager = LinearLayoutManager(requireContext())

    }

    private fun setupViewModel() {
        val noteDao = NoteDatabase.getDatabase(requireContext()).dao
        val factory = NoteViewModelFactory(noteDao)
        noteViewModel = ViewModelProvider(this, factory).get(NoteViewModel::class.java)

        lifecycleScope.launch {
            noteViewModel.getAllNoteByDate.collect { notes ->
                adapter.setData(notes)

            }
        }
    }


    private fun setupFloatingActionButton() {
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addNoteFragment)
        }
    }

    private fun observeNoteChanges() {
        lifecycleScope.launch {
            noteViewModel.getAllNoteByDate.collect { notes ->
                adapter.setData(notes)
            }
        }
    }

}