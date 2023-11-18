package com.example.notesapp.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.notesapp.domain.entities.NoteModel
import java.time.LocalDateTime
import javax.inject.Inject
import java.time.format.DateTimeFormatter

class Sorter @Inject constructor() {
    @RequiresApi(Build.VERSION_CODES.O)
    operator fun invoke(list: List<NoteModel>): List<NoteModel> {
        val formatter = DateTimeFormatter.ofPattern("HH:mm dd.MM.yyyy")
        return list.sortedByDescending { LocalDateTime.parse(it.dateTime, formatter) }
    }
}