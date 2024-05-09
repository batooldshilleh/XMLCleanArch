package com.example.xmlcleanarch.screens.retrofit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.xmlcleanarch.adapters.retrofitAdapter.PostRecyclerviewAdapter
import com.example.xmlcleanarch.databinding.FragmentGetPostsBinding
import com.example.xmlcleanarch.factory.RetrofitFactory
import com.example.xmlcleanarch.repository.retrofitRepository.PostRepository


class GetPostsFragment : Fragment() {

    private lateinit var binding: FragmentGetPostsBinding
    private lateinit var viewModel: RetrofitViewModel
    private val postAdapter by lazy { PostRecyclerviewAdapter() }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGetPostsBinding.inflate(inflater, container, false)
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        val repository = PostRepository()
        val viewModelFactory = RetrofitFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[RetrofitViewModel::class.java]
        setupRecyclerview()

        binding.btnGet.setOnClickListener {
            val userNumber: String = binding.etUserId.text.toString()
            val userNumberInt: Int = Integer.parseInt(userNumber)
            viewModel.getCustomPost(userNumberInt, "id", "desc")

            viewModel.apiResponseGetCustom.observe(viewLifecycleOwner) { response ->
                if (response.isSuccessful) {
                    response.body()?.let { postAdapter.setData(it) }
                } else {
                    Toast.makeText(requireContext(), response.code(), Toast.LENGTH_LONG).show()
                }

            }
        }
        return binding.root
    }

    private fun setupRecyclerview() {
        val recyclerView = binding.rvPosts

        recyclerView.adapter = postAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

    }
}