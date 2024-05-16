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
            val userNumberInt: Int = Integer.parseInt(userNumber)
            viewModel.getCustomPost(userNumberInt)

            viewModel.status.observe(viewLifecycleOwner) { status ->
                when (status) {
                    "loading" -> {

                    }
                    "success" -> {

                        viewModel.posts.value?.let { postAdapter.setData(it) }
                    }
                    else -> {

                        Toast.makeText(requireContext(), status, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}