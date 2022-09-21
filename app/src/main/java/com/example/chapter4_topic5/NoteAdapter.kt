package com.example.chapter4_topic5

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
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

            GlobalScope.async{
                val del = dbNote?.noteDAO()?.deleteNote(listNote[position])
                (holder.itemView.context as MainActivity).getAllNote()

                (holder.itemView.context as MainActivity).runOnUiThread {
                    if (del != 0){
                        Toast.makeText(it.context,"Data ${listNote[position].id } Success Deleted",Toast.LENGTH_SHORT).show()
                        (holder.itemView.context as MainActivity).getAllNote()

                    }else{
                        Toast.makeText(it.context,"Data ${listNote[position].id } Failed to Delete",Toast.LENGTH_SHORT).show()

                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return listNote.size
    }
}