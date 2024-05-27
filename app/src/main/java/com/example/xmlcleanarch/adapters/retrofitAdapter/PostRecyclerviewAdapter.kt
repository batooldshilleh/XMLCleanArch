package com.example.xmlcleanarch.adapters.retrofitAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.xmlcleanarch.data.retrofitModel.Post
import com.example.xmlcleanarch.databinding.PostItemBinding

class PostRecyclerviewAdapter : RecyclerView.Adapter<PostViewHolder>() {

    private var postList = mutableListOf<Post>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding =
            PostItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val currentItem = postList[position]
        holder.bind(currentItem)
    }

    fun setData(postsList: List<Post>) {
        val diffResult = DiffUtil.calculateDiff(PostDiffCallback(postList, postsList))
        postList.clear()
        postList.addAll(postsList)
        diffResult.dispatchUpdatesTo(this)
    }
}

class PostViewHolder(
    private val binding: PostItemBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        binding.tvUserId.text = post.userId.toString()
        binding.tvId.text = post.id.toString()
        binding.tvTitleText.text = post.title
        binding.tvBody.text = post.body
    }
}