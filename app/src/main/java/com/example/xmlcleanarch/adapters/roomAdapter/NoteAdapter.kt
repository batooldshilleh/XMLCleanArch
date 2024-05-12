package com.example.xmlcleanarch.adapters.roomAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.xmlcleanarch.data.roomdata.Note
import com.example.xmlcleanarch.databinding.RecyclerviewItemBinding


class NoteAdapter(private val deleteListener: NoteDeleteListener) :
    RecyclerView.Adapter<MyViewHolder>() {

    private var noteList = emptyList<Note>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            RecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding, deleteListener)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = noteList[position]
        holder.bind(currentItem)
    }

    fun setData(newNoteList: List<Note>) {
        val oldNoteListSize = noteList.size
        val newNoteListSize = newNoteList.size

        noteList = newNoteList

        val diffResult = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize(): Int {
                return oldNoteListSize
            }

            override fun getNewListSize(): Int {
                return newNoteListSize
            }

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return noteList[oldItemPosition].id == newNoteList[newItemPosition].id
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                val oldNote = noteList[oldItemPosition]
                val newNote = newNoteList[newItemPosition]
                return oldNote == newNote
            }
        })

        diffResult.dispatchUpdatesTo(this)
    }
}

class MyViewHolder(
    private val binding: RecyclerviewItemBinding,
    private val deleteListener: NoteDeleteListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(note: Note) {
        binding.tvTitle.text = note.title
        binding.tvDes.text = note.description

        binding.ivDelete.setOnClickListener {
            deleteListener.onDeleteClick(note)
        }
    }
}
