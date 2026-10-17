package com.example.notepad

data class DeletedNote(
    val index: Int,
    val note: NotePad
)

data class ScreenUiState(
    val note: List<NotePad> = emptyList(),
    val lastDeleted: DeletedNote? = null
)