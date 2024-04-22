package com.example.xmlcleanarch.adapters.roomAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.xmlcleanarch.R
import com.example.xmlcleanarch.data.roomdata.Note
import com.example.xmlcleanarch.databinding.RecyclerviewItemBinding
import com.example.xmlcleanarch.repository.roomRepository.NoteRepository
import com.example.xmlcleanarch.screens.roomdb.NoteViewModel

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.MyViewHolder>() {

    private var noteList = emptyList<Note>()
    private var deleteListener: ((Note) -> Unit)? = null




    inner class MyViewHolder(val binding: RecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root) {



        init {
            binding.ivDelete.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    showDeleteDialog(noteList[position])
                }
            }
        }
        private fun showDeleteDialog(note: Note) {
            val builder = AlertDialog.Builder(binding.root.context)
            builder.setTitle(R.string.delete_dialog_title)
                .setMessage(R.string.delete_dialog_message)
                .setPositiveButton(R.string.yes) { dialog, _ ->
                    deleteListener?.invoke(note)
                    removeItem(adapterPosition)
                    dialog.dismiss()
                }
                .setNegativeButton(R.string.no) { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }

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
    fun removeItem(position: Int) {
        noteList = noteList.filterIndexed { index, _ -> index != position }
        notifyItemRemoved(position)

    }
    fun setOnDeleteClickListener(listener: (Note) -> Unit) {
        this.deleteListener = listener
    }
}
