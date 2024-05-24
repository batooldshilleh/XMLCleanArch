package com.example.xmlcleanarch.screens.retrofit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.xmlcleanarch.R
import com.example.xmlcleanarch.databinding.FragmentMenueBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuFragment : Fragment() {

    private lateinit var binding: FragmentMenueBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenueBinding.inflate(inflater, container, false)
        setupOnClick()
        return binding.root
    }

    private fun setupOnClick() {
        binding.btnGetScreen.setOnClickListener {
            findNavController().navigate(R.id.action_menueFragment_to_getPostsFragment)
        }
        binding.btnPostScreen.setOnClickListener {
            findNavController().navigate(R.id.action_menueFragment_to_postPostFragment)
        }
    }
}