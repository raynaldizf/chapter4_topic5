package com.example.chapter4_topic5.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NoteDAO {
    @Insert
    fun insertNote(note : DataNote)

    @Query("SELECT * FROM datanote ORDER BY id desc")
    fun getDataNote() : List<DataNote>

    @Delete
    fun deleteNote(note : DataNote) : Int
}