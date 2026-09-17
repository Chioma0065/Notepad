package com.example.notepad

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ScreenViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ScreenUiState())

    val uiState = _uiState.asStateFlow()


    fun noteEntry() {
        _uiState.update { currentState ->
            val text = NotePad(title = currentState.userInput, entry = currentState.userInput)
            currentState.copy(
                note = currentState.note + text, userInput = ""
            )
        }
    }

    fun updateUserInput(text: String) {
        _uiState.update { it.copy(userInput = text) }
    }
}


