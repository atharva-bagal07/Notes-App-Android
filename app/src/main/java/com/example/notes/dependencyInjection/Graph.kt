package com.example.notes.dependencyInjection

import android.content.Context
import androidx.room.Room
import com.example.notes.repository.NotesRepository
import com.example.notes.room.NotesDatabase

object Graph {

    private lateinit var db: NotesDatabase

    val notesRepository by lazy {
        NotesRepository(db.notesDao())
    }

    fun initializeDB(context: Context) {
        db = Room.databaseBuilder(
            context,
            NotesDatabase::class.java,
            "notes.db"
        ).build()
    }
}