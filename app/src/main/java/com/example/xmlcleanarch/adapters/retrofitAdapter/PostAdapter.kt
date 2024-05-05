package com.example.xmlcleanarch.adapters.retrofitAdapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.xmlcleanarch.data.retrofitModel.Post
import com.example.xmlcleanarch.databinding.PostItemBinding

class PostAdapter : RecyclerView.Adapter<MyViewHolder>() {

    private var postList = emptyList<Post>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            PostItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = postList[position]
        holder.bind(currentItem)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(postsList: List<Post>) {
        this.postList = postsList
        notifyDataSetChanged()
    }
}

class MyViewHolder(
    private val binding: PostItemBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        binding.tvUserId.text = post.userId.toString()
        binding.tvId.text = post.id.toString()
        binding.tvTitleText.text = post.title
        binding.tvBody.text = post.body

    }
}