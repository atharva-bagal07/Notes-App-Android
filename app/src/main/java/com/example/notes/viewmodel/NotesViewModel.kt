package com.example.notes.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.dependencyInjection.Graph
import com.example.notes.repository.NotesRepository
import com.example.notes.room.NotesEntity
import com.example.notes.uistate.state.NotesState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NotesViewModel(
    private val notesRepository: NotesRepository = Graph.notesRepository
) : ViewModel() {

    private var titleState by mutableStateOf("")
    private var contentState by mutableStateOf("")

    private val _state = MutableStateFlow(NotesState())
    val state: StateFlow<NotesState> = _state.asStateFlow()
    fun onTitleChange(title: String) {
        titleState = title
    }

    fun onContentChange(title: String) {
        contentState = title
    }

    private lateinit var getAllNotes: Flow<List<NotesEntity>>

    init {
        viewModelScope.launch {
            getAllNotes = notesRepository.getAllNotes()
            getAllNotes.collect { list ->
                _state.update {
                    it.copy(
                        allNotes = list
                    )
                }
            }
        }
    }

    fun addNote(note: NotesEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.insertNote(note)
        }
    }

    fun updateNote(note: NotesEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.updateNote(note)
        }
    }

    fun deleteNote(note: NotesEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.deleteNote(note)
        }
    }

}
