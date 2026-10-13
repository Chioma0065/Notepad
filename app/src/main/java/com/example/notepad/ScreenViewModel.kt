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


    fun deleteNote(index: Int){
        _uiState.update { currentState ->
            if (index !in currentState.note.indices){
                currentState
            }else{
                currentState.copy(
                    note = currentState.note.filterIndexed {i, _ -> i != index }
                )
            }
        }
    }


}


