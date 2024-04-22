package com.example.xmlcleanarch.adapters.roomAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.xmlcleanarch.data.roomdata.Note
import com.example.xmlcleanarch.databinding.RecyclerviewItemBinding

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.MyViewHolder>() {

    private var noteList = emptyList<Note>()

    class MyViewHolder(val binding: RecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = noteList[position]
        holder.binding.tvTitle.text = currentItem.title
        holder.binding.tvDes.text = currentItem.description
    }

    fun setData(noteList: List<Note>) {
        this.noteList = noteList
        notifyDataSetChanged()
    }
}
