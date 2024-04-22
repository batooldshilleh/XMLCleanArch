package com.example.xmlcleanarch.screens.roomdb

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.xmlcleanarch.R
import com.example.xmlcleanarch.adapters.roomAdapter.NoteAdapter
import com.example.xmlcleanarch.databinding.ActivityMainBinding
import com.example.xmlcleanarch.databinding.ActivityRoomBinding

class roomActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRoomBinding
    private lateinit var viewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(findNavController(R.id.fragmentContainerView))

        val adapter = NoteAdapter()
        viewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        adapter.setOnDeleteClickListener { note ->
            viewModel.deleteNote(note)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragmentContainerView)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}