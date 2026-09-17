package com.example.notepad

data class ScreenUiState (
    var userInput : String = "",
    val note : List<NotePad> = emptyList()
)
