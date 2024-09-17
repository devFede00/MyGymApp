package com.example.mygym.ui.components.dialogs

import android.util.EventLogTags.Description
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DescriptionExerciseDialog(
    onDismiss: () -> Unit,
    description: String
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {},
        text = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Text(
                    text = description
                )
            }
        })
}

@Preview()
@Composable
fun DescriptionExerciseDialogPreview() {
    DescriptionExerciseDialog(onDismiss = { /*TODO*/ }, description = "Descrizione dell'esercizio")
}