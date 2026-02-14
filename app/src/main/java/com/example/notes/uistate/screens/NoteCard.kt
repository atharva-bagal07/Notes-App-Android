package com.example.notes.uistate.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NoteCard(title: String, content: String, onDelete: () -> Unit) {

    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.LightGray,
        ),
        modifier = Modifier
            .size(width = 240.dp, height = 180.dp)
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            Box(
                modifier = Modifier
                    .weight(0.3f)
                    .fillMaxWidth()
            ) {

                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(
                        text = title,
                        Modifier.padding(top = 16.dp, start = 16.dp),
                        textAlign = TextAlign.Center,
                        color = Color.Black, fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.width(48.dp))
                    IconButton(onClick = {
                        onDelete()
                    }) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = null)
                    }
                }
            }

            Box(modifier = Modifier.weight(0.7f)) {
                Text(
                    text = content,
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                )
            }
        }
    }

}

//@Preview
//@Composable
//fun NotePreview() {
//    NoteCard(title = "title", content = "Content")
//}