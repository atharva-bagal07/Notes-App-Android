package com.example.notes.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
abstract class NotesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertNote(note: NotesEntity)

    @Delete
    abstract fun deleteNote(note: NotesEntity)

    @Update
    abstract fun updateNote(note: NotesEntity)

    @Query("SELECT * FROM Notes")
    abstract fun getAllNotes(): Flow<List<NotesEntity>>
}