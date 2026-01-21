package com.example.notes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MainScreen() {

    val noteslist = remember {
        mutableStateListOf<NotesDetails>()
    }

    var showAddNote by remember { mutableStateOf(false) }

    if(showAddNote){
        AddNote {
            showAddNote = false
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.DarkGray)) {
        LazyVerticalGrid(GridCells.Fixed(count = 2)){
            items(noteslist){

            }
        }
        Button(
            onClick = { },
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