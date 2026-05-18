package com.example.notes.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notes.room.NotesEntity
import com.example.notes.viewmodel.NotesViewModel

@Composable
fun AddNote(viewModel: NotesViewModel, onBack: () -> Unit) {

    var title by remember {
        mutableStateOf("")
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
    ) {

        Column(modifier = Modifier.fillMaxSize()) {

            Box(
                modifier = Modifier

                    .weight(0.08f)
                    .fillMaxWidth()
                    .background(Color.Black), contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = "Add a Note",
                    modifier = Modifier.padding(start = 16.dp),
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace
                )
            }

            Box(
                modifier = Modifier
                    .weight(0.92f)
                    .fillMaxSize()
                    .background(Color.DarkGray)
            ) {


                TextField(
                    value = content,
                    onValueChange = { content = it },
                    placeholder = {
                        Text(
                            "Write a note..",
                            color = Color.LightGray.copy(alpha = 0.7f)
                        )
                    },
                    modifier = Modifier.fillMaxSize(),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.DarkGray,
                        unfocusedContainerColor = Color.DarkGray,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        cursorColor = Color.White
                    )
                )

            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.DarkGray)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Button(
                    onClick = { onBack() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFD300))
                ) {
                    Text(text = "Back", color = Color.Black)
                }
                Button(onClick = {
                    showTitleDialog = true

                }, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFD300))) {
                    Text(text = "Add", color = Color.Black)
                }
            }

        }
    }
    if (showTitleDialog) {
        AlertDialog(
            onDismissRequest = { showTitleDialog = false },
            title = {
                TextField(
                    value = title,
                    onValueChange = { title = it },
                    placeholder = { Text(text = "Add a title") }
                )
            },
            confirmButton = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Button(onClick = { showTitleDialog = false }) {
                        Text(text = "Cancel")
                    }
                    Button(onClick = {
                        viewModel.addNote(NotesEntity(title = title, content = content))
                        showTitleDialog = false
                        title = ""
                        content = ""

                    }
                    ) {
                        Text(text = "Save")
                    }
                }
            }
        )
    }
}