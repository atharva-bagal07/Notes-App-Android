package com.example.notes.uistate.state

import com.example.notes.room.NotesEntity

data class NotesState(
    val allNotes: List<NotesEntity> = emptyList(),
    val date: Long = System.currentTimeMillis(),
    val isAddingNote: Boolean = false
)