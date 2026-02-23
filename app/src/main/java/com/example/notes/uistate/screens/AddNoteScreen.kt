package com.example.notes.uistate.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import com.example.notes.room.NotesEntity
import com.example.notes.viewmodel.NotesViewModel

@Composable
fun AddNote(viewModel: NotesViewModel, onBack: () -> Unit) {
    var title by remember {
        mutableStateOf("Untitled")
    }

    var showTitleDialog by remember { mutableStateOf(false) }

    var content by remember {
        mutableStateOf("")
    }

    val state by viewModel.state.collectAsState()
    val focusRequester = remember { FocusRequester() }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = { onBack() }) {
                    Text(text = "Back")
                }
                Button(onClick = {
                    showTitleDialog = true

                }) {
                    Text(text = "Add")
                }
            }
            TextField(
                value = content,
                onValueChange = { content = it },
                modifier = Modifier.fillMaxSize()
            )

        }
    }
    if (showTitleDialog) {
        AlertDialog(
            onDismissRequest = { showTitleDialog = false },
            dismissButton = {
                Button(onClick = { showTitleDialog = false }) {
                    Text(text = "Cancel")
                }
            },
            title = {
                TextField(
                    value = title,
                    onValueChange = { title = it },
                    modifier = Modifier.focusRequester(focusRequester)
                )
            },
            confirmButton = {
                Button(onClick = {
                    viewModel.addNote(NotesEntity(title = title, content = content))
                    showTitleDialog = false
                    title = ""
                    content = ""

                }
                ) {
                    Text(text = "Save")
                }
            })
    }
}