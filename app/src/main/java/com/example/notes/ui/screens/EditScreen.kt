package com.example.notes.ui.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults

import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.notes.viewmodel.NotesViewModel


@Composable
fun EditScreen(
    viewModel: NotesViewModel,
    id: Int,
    onEditComplete: () -> Unit,
    onBack: () -> Unit
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    // Find the note using ID
    val note = state.allNotes.find { it.id == id }

    // If note is null, don’t render
    if (note == null) return

    var title by remember { mutableStateOf(note.title) }
    var content by remember { mutableStateOf(note.content) }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
            .padding(16.dp)
    ) {


        TextField(
            value = title,
            textStyle = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
            onValueChange = { title = it },

            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(scrollState),

            placeholder = { Text("Title") },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.DarkGray,
                unfocusedContainerColor = Color.DarkGray,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            ),
            singleLine = true

        )

        HorizontalDivider(color = Color.LightGray.copy(alpha = 0.5f))
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = content,
            onValueChange = { content = it },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            placeholder = { Text("Content") },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.DarkGray,
                unfocusedContainerColor = Color.DarkGray,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Button(
                onClick = {
                    onBack()
                }, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFD300))
            ) {
                Text("Back", color = Color.Black)
            }
            Button(
                onClick = {
                    val updatedNote = note.copy(
                        title = title,
                        content = content
                    )
                    viewModel.updateNote(updatedNote)
                    onEditComplete()
                }, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFD300))
            ) {
                Text("Save Changes", color = Color.Black)
            }
        }
    }
}
