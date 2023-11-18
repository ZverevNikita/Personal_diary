package com.example.notesapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.domain.Repository
import com.example.notesapp.domain.entities.NoteModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {

    private val _liveData = MutableLiveData<List<NoteModel>>()
    val liveData: LiveData<List<NoteModel>> get() = _liveData

    fun getNotes() {
        viewModelScope.launch {
            _liveData.value = repository.getNotes()
        }
    }

    fun addNote(note: NoteModel) {
        viewModelScope.launch {
            repository.addNote(note)
        }
    }

    fun changeNote(note: NoteModel) {
        viewModelScope.launch {
            repository.changeNote(note)
        }
    }

    fun deleteNote(id: Int) {
        viewModelScope.launch {
            repository.deleteNote(id)
        }
    }

    fun getByTag1(tag: String) {
        viewModelScope.launch {
            _liveData.value = repository.getByTag1(tag)
        }
    }

    fun getByTag2(tag: String) {
        viewModelScope.launch {
            _liveData.value = repository.getByTag2(tag)
        }
    }

    fun deleteAll() {
        viewModelScope.launch {
            repository.deleteAll()
        }
    }
}