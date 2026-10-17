package com.example.notepad

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ScreenViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ScreenUiState())

    val uiState = _uiState.asStateFlow()


    fun noteEntry(title: String, entry: String) {
        _uiState.update { currentState ->
            currentState.copy(
                note = currentState.note + NotePad(title = title, entry = entry)
            )

        }
    }

    fun updateNote(index: Int, title: String, entry: String) {
        _uiState.update { currentState ->
            val updatedList = currentState.note.toMutableList()
            updatedList[index] = NotePad(title, entry = entry)
            currentState.copy(note = updatedList)

        }
    }


    fun deleteNote(index: Int) {
        _uiState.update { currentState ->
            val removed = currentState.note.getOrNull(index)
            if (removed == null) {
                currentState
            } else {
                currentState.copy(
                    note = currentState.note.filterIndexed { i, _ -> i != index },
                    lastDeleted = DeletedNote(index, removed)
                )
            }
        }
    }

    fun undoDelete() {
        _uiState.update { currentState ->
            val deleted = currentState.lastDeleted
            if (deleted == null) {
                currentState
            } else {
                val restored = currentState.note.toMutableList()
                restored.add(deleted.index.coerceIn(0, restored.size), deleted.note)
                currentState.copy(note = restored, lastDeleted = null)
            }
        }
    }

    fun clearLastDeleted() {
        _uiState.update { it.copy(lastDeleted = null) }
    }
}

