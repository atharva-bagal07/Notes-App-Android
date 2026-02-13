package com.example.notes.repository

import com.example.notes.room.NotesDao
import com.example.notes.room.NotesEntity
import kotlinx.coroutines.flow.Flow

class NotesRepository(private val notesDao: NotesDao) {
    suspend fun updateNote(note: NotesEntity) {
        notesDao.updateNote(note)
    }

    suspend fun deleteNote(note: NotesEntity) {
        notesDao.deleteNote(note = note)
    }

    fun getAllNotes(): Flow<List<NotesEntity>> = notesDao.getAllNotes()

    suspend fun insertNote(note: NotesEntity) {
        notesDao.insertNote(note)
    }
}