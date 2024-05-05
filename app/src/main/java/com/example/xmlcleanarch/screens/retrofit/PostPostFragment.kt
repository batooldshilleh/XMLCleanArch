package com.example.xmlcleanarch.screens.retrofit

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.xmlcleanarch.databinding.FragmentPostPostBinding
import com.example.xmlcleanarch.factory.RetrofitFactory
import com.example.xmlcleanarch.repository.retrofitRepository.PostRepository


class PostPostFragment : Fragment() {

    private lateinit var binding: FragmentPostPostBinding
    private lateinit var viewModel: RetrofitViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPostPostBinding.inflate(inflater, container, false)

        val repository = PostRepository()
        val viewModelFactory = RetrofitFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[RetrofitViewModel::class.java]

        binding.btnPost.setOnClickListener {

            val userId = binding.etUserIDPost.text.toString()
            val id = binding.etIdPost.text.toString()
            val title = binding.etTitlePost.text.toString()
            val body = binding.etBodyPost.text.toString()

            val userNumberInt = userId.toIntOrNull()
            val idInt = id.toIntOrNull()

            if (userNumberInt != null && idInt != null) {

                viewModel.pushPost(userNumberInt, idInt, title, body)

                viewModel.apiResponsePost.observe(viewLifecycleOwner) { response ->
                    if (response.isSuccessful) {
                        clearEditText()
                        Toast.makeText(
                            requireContext(),
                            response.code().toString(),
                            Toast.LENGTH_LONG
                        ).show()
                        Log.d("response body", response.body().toString())
                        Log.d("response message", response.message().toString())
                    } else {
                        Toast.makeText(
                            requireContext(),
                            response.code().toString(),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            } else {
                Toast.makeText(requireContext(), "Please enter valid numbers", Toast.LENGTH_LONG)
                    .show()
            }
        }

        return binding.root
    }

    private fun clearEditText() {
        binding.etUserIDPost.text.clear()
        binding.etIdPost.text.clear()
        binding.etTitlePost.text.clear()
        binding.etBodyPost.text.clear()
    }
}