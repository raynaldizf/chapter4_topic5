package com.example.chapter4_topic5

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chapter4_topic5.databinding.ActivityMainBinding
import com.example.chapter4_topic5.room.DataNote
import com.example.chapter4_topic5.room.NoteDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    var noteDB : NoteDatabase? = null
    lateinit var adapterNote : NoteAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        noteDB = NoteDatabase.getInstance(this)


        getAllNote()

        binding.btnAddNote.setOnClickListener{
            startActivity(Intent(this,AddNoteActivity::class.java))
        }

    }
    fun getAllNote(){
        GlobalScope.launch {
            var db = noteDB!!.noteDAO().getDataNote()
            adapterNote = NoteAdapter(db)
            binding.rvNote.layoutManager = LinearLayoutManager(applicationContext,LinearLayoutManager.VERTICAL,false)
            binding.rvNote.adapter = adapterNote

//            runOnUiThread{
//                db.let {
//                    adapterNote = NoteAdapter(it)
//                    binding.rvNote.layoutManager = LinearLayoutManager(applicationContext,LinearLayoutManager.VERTICAL,false)
//                    binding.rvNote.adapter = adapterNote
//                }
//            }
        }
    }

    override fun onStart() {
        super.onStart()
        GlobalScope.launch {
            var db = noteDB!!.noteDAO().getDataNote()
//            adapterNote = NoteAdapter(db!!)
//            binding.rvNote.layoutManager = LinearLayoutManager(applicationContext,LinearLayoutManager.VERTICAL,false)
//            binding.rvNote.adapter = adapterNote

            runOnUiThread{
                db.let {
                    adapterNote = NoteAdapter(it!!)
                    binding.rvNote.layoutManager = LinearLayoutManager(applicationContext,LinearLayoutManager.VERTICAL,false)
                    binding.rvNote.adapter = adapterNote
                }
            }
        }
    }
}