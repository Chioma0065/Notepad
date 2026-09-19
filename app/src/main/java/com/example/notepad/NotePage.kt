package com.example.notepad

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ScreenA(
    navController: NavController, screenViewModel: ScreenViewModel
) {
    val uiState by screenViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .safeDrawingPadding()


    ) {

        ElevatedCard(
            shape = RoundedCornerShape(10.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .height(40.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "NOTEPAD",
                    fontSize = 25.sp,
                    letterSpacing = 5.sp,
                    fontStyle = FontStyle.Italic
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

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
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (uiState.note.isEmpty()) {
                Text("No notes")
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    itemsIndexed(
                        uiState.note
                    ) { index, note ->

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .clickable {
                                    navController.navigate(Routes.screenBRoutes(index))
                                }) {
                            Column(
                                modifier = Modifier.padding(12.dp)
                            ) {
                                Text(text = note.title, fontWeight = FontWeight.Bold)
                                Text(
                                    text = note.entry, maxLines = 2
                                )
                            }
                        }

                    }
                }
            }


        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp), contentAlignment = Alignment.CenterEnd
        ) {
            IconButton(onClick = {
                navController.navigate(Routes.screenBRoutes(-1))
            }) {

                Icon(
                    painter = painterResource(R.drawable.baseline_add_24),
                    contentDescription = "Add note"
                )
            }
        }
    }

}
