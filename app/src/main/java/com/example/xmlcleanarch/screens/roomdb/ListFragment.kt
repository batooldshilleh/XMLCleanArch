package com.example.xmlcleanarch.screens.roomdb

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.xmlcleanarch.R
import com.example.xmlcleanarch.adapters.roomAdapter.NoteAdapter
import com.example.xmlcleanarch.adapters.roomAdapter.NoteDeleteListener
import com.example.xmlcleanarch.data.roomdata.Note
import com.example.xmlcleanarch.data.roomdata.NoteDatabase
import com.example.xmlcleanarch.databinding.FragmentListBinding
import com.example.xmlcleanarch.factory.NoteViewModelFactory
import kotlinx.coroutines.launch


class ListFragment : Fragment(), NoteDeleteListener {

    private lateinit var binding: FragmentListBinding
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var adapter: NoteAdapter
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
        adapter = NoteAdapter(this)
        binding.rvNote.adapter = adapter
        binding.rvNote.layoutManager = LinearLayoutManager(requireContext())

    }

    private fun setupViewModel() {
        val noteDao = NoteDatabase.getDatabase(requireContext()).dao
        val factory = NoteViewModelFactory(noteDao)
        noteViewModel = ViewModelProvider(this, factory)[NoteViewModel::class.java]

        lifecycleScope.launch {
            noteViewModel.allNoteByDate.collect { notes ->
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
            noteViewModel.allNoteByDate.collect { notes ->
                adapter.setData(notes)
            }
        }
    }

    override fun onDeleteClick(note: Note) {
        showDeleteDialog(note)
    }

    private fun showDeleteDialog(note: Note) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(R.string.delete_dialog_title)
            .setMessage(R.string.delete_dialog_message)
            .setPositiveButton(R.string.yes) { dialog, _ ->
                noteViewModel.deleteNote(note)
                dialog.dismiss()
            }
            .setNegativeButton(R.string.no) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

}