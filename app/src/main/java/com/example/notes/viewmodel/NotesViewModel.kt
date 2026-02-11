package com.example.notes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.room.NotesDao
import com.example.notes.room.NotesEntity
import com.example.notes.uistate.events.NotesEvent
import com.example.notes.uistate.state.NotesState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NotesViewModel(
    private val dao: NotesDao
) : ViewModel() {


    private val _state = MutableStateFlow(NotesState())
    val state: StateFlow<NotesState> = _state.asStateFlow()


    init {
        viewModelScope.launch {
            dao.getAllNotes().collect { notesList ->
                _state.update {
                    it.copy(allNotes = notesList)
                }
            }
        }
    }

    fun onEvent(event: NotesEvent) {

        when (event) {

            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    dao.deleteNote(event.note)
                }
            }

            is NotesEvent.SaveNote -> {

                val title = event.title
                val content = event.content

                if (title.isBlank() || content.isBlank()) {
                    return
                }

                val note = NotesEntity(title = title, content = content)
                viewModelScope.launch {
                    dao.upsertNote(note)
                }

                _state.update {
                    it.copy(
                        isAddingNote = false
                    )
                }
            }
        }
    }
}
