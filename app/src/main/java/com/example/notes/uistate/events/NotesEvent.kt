package com.example.notes.uistate.events

import com.example.notes.room.NotesEntity

sealed interface NotesEvent {
    data class SaveNote(
        val title: String,
        val content: String
    ) : NotesEvent

    data class DeleteNote(val note: NotesEntity) : NotesEvent
}