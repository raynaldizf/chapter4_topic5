package com.example.chapter4_topic5

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.chapter4_topic5.databinding.ActivityAddNoteBinding
import com.example.chapter4_topic5.room.NoteDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class AddNoteActivity : AppCompatActivity() {
    lateinit var binding : ActivityAddNoteBinding
    var dbNote : NoteDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbNote = NoteDatabase.getInstance(this)

        binding.btnSaveNote.setOnClickListener{
            addNote()
        }
    }

    fun addNote(){
        GlobalScope.async {
            var title = binding.noteTitle.text.toString()
            var note = binding.content.text.toString()
        }
    }
}