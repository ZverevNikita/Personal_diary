package com.example.notesapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NoteEntity::class], version = 1)
abstract class DataBase : RoomDatabase() {
    abstract fun getDao(): Dao
}