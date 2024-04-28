package com.example.xmlcleanarch.adapters.roomAdapter

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.xmlcleanarch.R
import com.example.xmlcleanarch.data.roomdata.Note
import com.example.xmlcleanarch.databinding.RecyclerviewItemBinding


class NoteAdapter(private var onDeleteClickListener: (Note) -> Unit) :
    RecyclerView.Adapter<MyViewHolder>() {

    private var noteList = emptyList<Note>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            RecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding, onDeleteClickListener)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = noteList[position]
        holder.bind(currentItem)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(noteList: List<Note>) {
        this.noteList = noteList
        this.notifyDataSetChanged()
    }

}

class MyViewHolder(
    private val binding: RecyclerviewItemBinding,
    private val onDeleteClickListener: (Note) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(note: Note) {
        binding.tvTitle.text = note.title
        binding.tvDes.text = note.description

        binding.ivDelete.setOnClickListener {
            showDeleteDialog(context = it.context, note = note)
        }
    }

    private fun showDeleteDialog(context: Context, note: Note) {
        val builder = AlertDialog.Builder(context)

        builder.setTitle(R.string.delete_dialog_title)
            .setMessage(R.string.delete_dialog_message)
            .setPositiveButton(R.string.yes) { dialog, _ ->
                onDeleteClickListener(note)
                dialog.dismiss()
            }
            .setNegativeButton(R.string.no) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}
