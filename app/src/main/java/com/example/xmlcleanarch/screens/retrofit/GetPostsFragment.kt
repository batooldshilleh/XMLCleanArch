package com.example.xmlcleanarch.screens.retrofit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.xmlcleanarch.adapters.retrofitAdapter.PostRecyclerviewAdapter
import com.example.xmlcleanarch.databinding.FragmentGetPostsBinding
import com.example.xmlcleanarch.util.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GetPostsFragment : Fragment() {

    private lateinit var binding: FragmentGetPostsBinding
    private val viewModel: RetrofitViewModel by viewModels()
    private val postAdapter by lazy { PostRecyclerviewAdapter() }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGetPostsBinding.inflate(inflater, container, false)
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        observeViewModel()
        setupRecyclerview()
        btnClickListener()
        return binding.root
    }

    private fun setupRecyclerview() {
        val recyclerView = binding.rvPosts
        recyclerView.adapter = postAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun btnClickListener() {
        binding.btnGet.setOnClickListener {
            val userNumber: String = binding.etUserId.text.toString()
            viewModel.validateAndGetCustomPost(userNumber)
        }
    }

    private fun observeViewModel() {
        viewModel.validationStatus.observe(viewLifecycleOwner) { validationMessage ->
            Toast.makeText(requireContext(), validationMessage, Toast.LENGTH_LONG).show()
        }
        viewModel.status.observe(viewLifecycleOwner) { status ->
            when (status) {
                Status.LOADING.toString() -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                Status.SUCCESS.toString() -> {
                    binding.progressBar.visibility = View.GONE
                    viewModel.posts.value?.let {
                        postAdapter.setData(it)
                    }
                    Toast.makeText(requireContext(), "success", Toast.LENGTH_LONG).show()
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
}