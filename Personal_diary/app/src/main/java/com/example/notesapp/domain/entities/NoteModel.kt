package com.example.notesapp.domain.entities

data class NoteModel(
    val id: Int,
    val title: String,
    val message: String,
    val dateTime: String,
    val tag1: String,
    val tag2: String
)
