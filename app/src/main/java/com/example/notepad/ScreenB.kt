package com.example.notepad


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ScreenB(navController: NavController, viewModel: ScreenViewModel) {
    var title by remember { mutableStateOf("") }
    var text by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .safeDrawingPadding(),

        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row {
            TextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("TITLE") },
                singleLine = true,
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.padding(5.dp)
            )

            Spacer(
                modifier = Modifier.width(2.dp)
            )

            IconButton(onClick = {
                viewModel.noteEntry(title, text)
                navController.popBackStack()
            }) {
                Icon(
                    painter = painterResource(R.drawable.baseline_done_24),
                    contentDescription = "Done"
                )
            }
        }

        BasicTextField(
            value = text,
            onValueChange = { text = it },
            cursorBrush = SolidColor(Color.Black),
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
                .verticalScroll(rememberScrollState())
        )


    }


}