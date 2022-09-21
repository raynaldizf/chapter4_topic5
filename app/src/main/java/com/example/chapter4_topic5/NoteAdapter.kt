package com.example.chapter4_topic5

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chapter4_topic5.databinding.ItemNotesBinding
import com.example.chapter4_topic5.room.DataNote
import com.example.chapter4_topic5.room.NoteDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class NoteAdapter(var listNote : List<DataNote>) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
    var dbNote : NoteDatabase? = null
    class ViewHolder(var binding : ItemNotesBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = ItemNotesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.noteId.text = listNote[position].id.toString()
        holder.binding.noteTitle.text = listNote[position].content
        holder.binding.deleteNote.setOnClickListener{
            dbNote = NoteDatabase.getInstance(it.context)

            GlobalScope.async {
                dbNote?.noteDAO()?.deleteNote(listNote[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return listNote.size
    }
}