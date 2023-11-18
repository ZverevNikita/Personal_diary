package com.example.notesapp.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "message") val message: String,
    @ColumnInfo(name = "date_time") val dateTime: String,
    @ColumnInfo(name = "tag1") val tag1: String,
    @ColumnInfo(name = "tag2") val tag2: String
)

