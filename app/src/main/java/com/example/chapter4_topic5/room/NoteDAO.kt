package com.example.chapter4_topic5.room

import androidx.room.Dao
import androidx.room.Insert
@Dao
interface NoteDAO {
    @Insert
    fun insertNote(note : DataNote)
}