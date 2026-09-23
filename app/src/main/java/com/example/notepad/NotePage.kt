package com.example.notepad

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenA(
    navController: NavController, screenViewModel: ScreenViewModel
) {
    val uiState by screenViewModel.uiState.collectAsState()

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    text = "NOTEPAD",
                )
            })
    }, floatingActionButton = {
        FloatingActionButton(
            onClick = {
                navController.navigate(Routes.screenBRoutes(-1))
            }) {
            Icon(
                painter = painterResource(R.drawable.baseline_add_24),
                contentDescription = "Add note"
            )
        }
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            OutlinedCard(
                border = BorderStroke(1.dp, Color.Black), modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "My Notes", modifier = Modifier.padding(8.dp)
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (uiState.note.isEmpty()) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "No notes yet",
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        Text(
                            text = "Tap + to write your first note",
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                        )
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        itemsIndexed(uiState.note) { index, note ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                                    .clickable {
                                        navController.navigate(Routes.screenBRoutes(index))
                                    },
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                                ),
                                elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
                            ) {
                                Column(
                                    modifier = Modifier.padding(12.dp)
                                ) {
                                    Text(text = note.title,
                                        fontWeight = FontWeight.Bold)
                                    Text(
                                        text = note.entry,
                                        maxLines = 2,
                                        color = MaterialTheme.colorScheme.onSecondaryContainer.copy(
                                            alpha = 0.7f
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}