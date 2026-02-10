package com.example.notes.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {
    @Upsert
    suspend fun upsertNote(note: NotesEntity)

    @Delete
    suspend fun deleteNote(note: NotesEntity)

    @Query("SELECT * FROM Notes")
    suspend fun getAllNotes(): Flow<List<NotesEntity>>
}