package com.example.notepad

object Routes {
    val screenA = "screenA"
    val screenB = "screenB/{noteIndex}"

    fun screenBRoutes(noteIndex: Int) = "screen_B/$noteIndex"

}