package com.example.notes.uistate.events

import com.example.notes.room.NotesEntity

sealed interface NotesEvent {
    object SaveNote : NotesEvent
    data class SetTitle(val title: String) : NotesEvent
    data class SetContent(val content: String) : NotesEvent
    object ShowAddScreen : NotesEvent
    object HideAddScreen : NotesEvent
    data class DeleteNote(val note: NotesEntity) : NotesEvent
}