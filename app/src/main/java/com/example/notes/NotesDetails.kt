package com.example.notes

import java.util.Date

data class NotesDetails(
    var title: String = "Untitled",
    val content: String,
    val date: Date? = null
)

