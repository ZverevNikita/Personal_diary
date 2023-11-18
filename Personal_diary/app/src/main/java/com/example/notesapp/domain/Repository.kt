package com.example.notesapp.domain

import com.example.notesapp.domain.entities.NoteModel

interface Repository {
    suspend fun getNotes(): List<NoteModel>
    suspend fun addNote(note: NoteModel)
    suspend fun changeNote(note: NoteModel)
    suspend fun deleteNote(id: Int)
    suspend fun getByTag1(tag: String): List<NoteModel>
    suspend fun getByTag2(tag: String): List<NoteModel>
    suspend fun deleteAll()
}