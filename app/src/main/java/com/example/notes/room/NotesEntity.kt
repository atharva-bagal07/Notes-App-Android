package com.example.notes.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Notes")
data class NotesEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo("Title")
    var title: String,
    @ColumnInfo("Content")
    val content: String,
    val date: Long = System.currentTimeMillis()
)