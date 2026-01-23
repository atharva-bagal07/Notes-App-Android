package com.example.notes.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "Notes")
data class NotesEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var title: String = "Untitled",
    val content: String,
    val date: Long = System.currentTimeMillis()
)

