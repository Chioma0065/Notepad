package com.example.notepad

object Routes {
    val screenA = "Screen_A"
    val screenB = "Screen_B/{noteIndex}"

    fun screenBRoutes(noteIndex: Int) = "Screen_B/$noteIndex"

}