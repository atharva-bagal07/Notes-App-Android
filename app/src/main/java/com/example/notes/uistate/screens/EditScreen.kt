package com.example.notes.uistate.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.notes.viewmodel.NotesViewModel

@Composable
fun EditScreen(viewModel: NotesViewModel, onEditComplete: () -> Unit, id: Int) {
    Column(modifier = Modifier.fillMaxSize()) {
        TextField(value = "atharva", onValueChange = {})
    }
}