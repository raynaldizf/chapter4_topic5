package com.example.chapter4_topic5.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DataNote(
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    var title : String,
    var content : String
)
