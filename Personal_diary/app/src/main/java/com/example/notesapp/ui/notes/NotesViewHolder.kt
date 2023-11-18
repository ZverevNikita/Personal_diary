package com.example.notesapp.ui.notes

import android.annotation.SuppressLint
import android.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.databinding.NoteLayoutBinding
import com.example.notesapp.domain.entities.NoteModel

class NotesViewHolder(
    private val binding: NoteLayoutBinding,
    private val itemClick: (note: NoteModel) -> Unit,
    private val itemDelete: (id: Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bind(note: NoteModel) {
        itemView.setOnClickListener {
            itemClick(note)
        }
        itemView.setOnLongClickListener {
            showDeleteConfirmationDialog(note.id)
            true
        }
        with(binding) {
            titleView.text = note.title
            messageView.text = note.message
            tag1View.text = "Тег1: " + note.tag1
            tag2View.text = "Тег2: " + note.tag2
            dateView.text = note.dateTime
        }
    }

    private fun showDeleteConfirmationDialog(id: Int) {
        val alertDialogBuilder = AlertDialog.Builder(itemView.context)
        alertDialogBuilder.setMessage(itemView.context.getString(R.string.delete_question))

        alertDialogBuilder.setPositiveButton(itemView.context.getString(R.string.yes)) { _, _ ->
            itemDelete(id)
        }

        alertDialogBuilder.setNegativeButton(itemView.context.getString(R.string.no)) { dialog, _ ->
            dialog.cancel()
        }
        alertDialogBuilder.show()
    }
}