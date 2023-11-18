package com.example.notesapp.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.notesapp.data.database.Dao
import com.example.notesapp.domain.Repository
import com.example.notesapp.domain.entities.NoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val dao: Dao,
    private val mapper: Mapper,
    private val sorter: Sorter
) : Repository {

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getNotes(): List<NoteModel> {
        return withContext(Dispatchers.IO) {
            val notes = dao.getAll().map { mapper.mapFromEntity(it) }
            sorter(notes)
        }
    }

    override suspend fun addNote(note: NoteModel) {
        runBlocking(Dispatchers.IO) {
            dao.insert(mapper.mapToEntity(note))
        }
    }

    override suspend fun changeNote(note: NoteModel) {
        withContext(Dispatchers.IO) {
            dao.update(note.id, note.title, note.message, note.dateTime, note.tag1, note.tag2)
        }
    }

    override suspend fun deleteNote(id: Int) {
        runBlocking(Dispatchers.IO) {
            dao.delete(id)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getByTag1(tag: String): List<NoteModel> {
        return withContext(Dispatchers.IO) {
            val notes = dao.getByTag1(tag).map { mapper.mapFromEntity(it) }
            sorter(notes)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getByTag2(tag: String): List<NoteModel> {
        return withContext(Dispatchers.IO) {
            val notes = dao.getByTag2(tag).map { mapper.mapFromEntity(it) }
            sorter(notes)
        }
    }

    override suspend fun deleteAll() {
        withContext(Dispatchers.IO) {
            dao.deleteAll()
        }
    }
}