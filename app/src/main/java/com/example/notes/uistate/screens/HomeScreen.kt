package com.example.notes.uistate.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.notes.viewmodel.NotesViewModel


@Composable
fun HomeScreen(viewModel: NotesViewModel, onAddClick: () -> Unit) {

    val state by viewModel.state.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
    ) {
        LazyVerticalGrid(GridCells.Fixed(count = 2)) {
            items(state.allNotes) { notesDetails ->
                Column {
                    Text(text = notesDetails.title)
                    Text(text = notesDetails.content)
                }
            }
        }
        Button(
            onClick = { onAddClick() },
            modifier = Modifier
                .clip(shape = RoundedCornerShape(50))
                .align(Alignment.BottomEnd)
                .padding(16.dp)
                .padding(bottom = 16.dp, end = 16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFD300))
        )
        {
            Text(text = "+", color = Color.Black)
        }
    }
}