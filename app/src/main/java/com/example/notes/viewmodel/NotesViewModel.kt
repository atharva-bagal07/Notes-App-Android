package com.example.notes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.room.NotesDao
import com.example.notes.uistate.events.NotesEvent
import com.example.notes.uistate.state.NotesState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NotesViewModel(
    private val dao: NotesDao
) : ViewModel() {

    private val _state = MutableStateFlow(NotesState())
    val state = _state.asStateFlow()

    fun onEvent(event: NotesEvent) {

        when (event) {

            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    dao.deleteNote(event.note)
                }
            }

            NotesEvent.HideAddScreen -> {
                _state.update {
                    it.copy(
                        isAddingNote = false
                    )
                }
            }

            NotesEvent.SaveNote -> {


            }

            is NotesEvent.SetContent -> {
                _state.update {
                    it.copy(
                        content = event.content
                    )
                }
            }

            is NotesEvent.SetTitle -> {
                _state.update {
                    it.copy(
                        title = event.title
                    )
                }
            }

            NotesEvent.ShowAddScreen ->
                _state.update {
                    it.copy(
                        isAddingNote = true
                    )
                }
        }
    }
}
