package com.example.xmlcleanarch.screens.retrofit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.xmlcleanarch.databinding.FragmentPostPostBinding
import com.example.xmlcleanarch.util.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostPostFragment : Fragment() {

    private lateinit var binding: FragmentPostPostBinding
    private val viewModel: RetrofitViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPostPostBinding.inflate(inflater, container, false)
        binding.btnPost.setOnClickListener {

            val userId = binding.etUserIDPost.text.toString()
            val id = binding.etIdPost.text.toString()
            val title = binding.etTitlePost.text.toString()
            val body = binding.etBodyPost.text.toString()

            viewModel.validateAndPushPost(userId, id, title, body)
            observeViewModel()
        }
        return binding.root
    }

    private fun observeViewModel() {
        viewModel.validationStatus.observe(viewLifecycleOwner) { validationMessage ->
            Toast.makeText(requireContext(), validationMessage, Toast.LENGTH_LONG).show()
        }
        viewModel.status.observe(viewLifecycleOwner) { status ->
            when (status) {
                Status.LOADING.toString() -> {
                    binding.progressBar.visibility = View.VISIBLE
                    Toast.makeText(requireContext(), "Loading...", Toast.LENGTH_SHORT).show()
                }
                Status.SUCCESS.toString() -> {
                    binding.progressBar.visibility = View.GONE
                    clearEditText()
                    Toast.makeText(requireContext(), "Post successful!", Toast.LENGTH_LONG).show()
                    viewModel.apiResponsePost.value?.let { response ->
                        Toast.makeText(
                            requireContext(),
                            response.code().toString(),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
                Status.ERROR.toString() -> {
                    binding.progressBar.visibility = View.GONE
                    viewModel.errorMessage.value?.let { error ->
                        Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()

                    }
                }
            }
        }
    }

    private fun clearEditText() {
        binding.etUserIDPost.text.clear()
        binding.etIdPost.text.clear()
        binding.etTitlePost.text.clear()
        binding.etBodyPost.text.clear()
    }
}