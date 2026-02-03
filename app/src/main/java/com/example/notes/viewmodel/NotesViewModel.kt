package com.example.notes.viewmodel

import androidx.lifecycle.ViewModel
import com.example.notes.room.NotesDao
import com.example.notes.uistate.events.NotesEvent
import com.example.notes.uistate.state.NotesState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NotesViewModel(
    private val dao: NotesDao
) : ViewModel() {

    private val _state = MutableStateFlow(NotesState())
    val state = _state.asStateFlow()

    fun onEvent(event: NotesEvent) {

        when (event) {

            is NotesEvent.DeleteNote -> TODO()
            NotesEvent.HideAddScreen -> {
                _state.update {
                    it.copy(
                        isAddingNote = false
                    )
                }
            }

            NotesEvent.SaveNote -> TODO()
            is NotesEvent.SetContent -> TODO()
            is NotesEvent.SetTitle -> TODO()
            NotesEvent.ShowAddScreen ->
                _state.update {
                    it.copy(
                        isAddingNote = true
                    )
                }

            else -> Unit
        }
    }
}
