package com.example.notesapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface Dao {

    @Query("SELECT * FROM notes_table")
    fun getAll(): List<NoteEntity>

    @Insert
    fun insert(note: NoteEntity)

    @Query("UPDATE notes_table SET title = :newTitle, message = :newMessage, date_time = :dateTime, tag1 = :tag1, tag2 = :tag2 WHERE id = :id")
    fun update(
        id: Int,
        newTitle: String,
        newMessage: String,
        dateTime: String,
        tag1: String,
        tag2: String
    )

    @Query("DELETE FROM notes_table WHERE id = :noteId")
    fun delete(noteId: Int)

    @Query("SELECT * FROM notes_table WHERE tag1 = :noteTag")
    fun getByTag1(noteTag: String): List<NoteEntity>

    @Query("SELECT * FROM notes_table WHERE tag2 = :noteTag")
    fun getByTag2(noteTag: String): List<NoteEntity>

    @Query("DELETE FROM notes_table")
    fun deleteAll()
}