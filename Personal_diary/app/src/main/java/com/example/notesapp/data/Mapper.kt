package com.example.notesapp.data

import com.example.notesapp.data.database.NoteEntity
import com.example.notesapp.domain.entities.NoteModel
import javax.inject.Inject

class Mapper @Inject constructor() {
    fun mapFromEntity(entity: NoteEntity): NoteModel =
        with(entity) {
            NoteModel(
                id = id,
                title = title,
                message = message,
                dateTime = dateTime,
                tag1 = tag1,
                tag2 = tag2
            )
        }

    fun mapToEntity(note: NoteModel): NoteEntity =
        with(note) {
            NoteEntity(
                title = title,
                message = message,
                dateTime = dateTime,
                tag1 = tag1,
                tag2 = tag2
            )
        }
}