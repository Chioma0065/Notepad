package com.example.notepad

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()
            val screenViewModel: ScreenViewModel = viewModel()
            NavHost(navController = navController, startDestination = Routes.screenA) {
                composable(Routes.screenA) {
                    ScreenA(
                        navController, screenViewModel
                    )

                }
                composable(Routes.screenB) {
                    ScreenB(navController, screenViewModel)
                }
            }

        }
    }
}

