package com.example.mygym.ui.components.dialogs

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.mygym.ui.theme.MyGymTheme

@Composable
fun AddExerciseDialog(
    onConfirm: (String,String,String,String) -> Unit = {name,series,repetitions,recovery ->},
    onDismiss: () -> Unit = {}
) {

    val context = LocalContext.current
    var name by remember { mutableStateOf("") }
    var series by remember { mutableStateOf("") }
    var repetitions by remember { mutableStateOf("") }
    var recovery by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Aggiungi Nuovo Elemento") },
        text = {
            Column {
                val focusManager = LocalFocusManager.current
                OutlinedTextField(
                    value = name,
                    onValueChange = { if (it.length <= 30) name = it },
                    label = { Text("Nome Esercizio") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    )
                )
                OutlinedTextField(
                    value = series,
                    onValueChange = { if (it.length <= 2) series = it  },
                    label = { Text("Serie di esercizi") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    )
                )
                OutlinedTextField(
                    value = repetitions,
                    onValueChange = { if (it.length <= 2) repetitions = it },
                    label = { Text("Numero di ripetizioni") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    )
                )
                OutlinedTextField(
                    value = recovery,
                    onValueChange = { if (it.length <= 2) recovery = it },
                    label = { Text("Tempo di recupero (in secondi)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done)
                )
            }
        },
        confirmButton = {
            OutlinedButton(
                onClick = {
                    if (arrayOf(name, series, repetitions, recovery).all { it.isNotBlank() }) {
                        onConfirm(name, series, repetitions, recovery)
                    }
                    else{
                        Toast.makeText(context,"Tutti i campi devono essere riempiti",Toast.LENGTH_SHORT).show()
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text("Aggiungi")
            }
        },
        dismissButton = {
            Button(
                onClick = onDismiss,
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text("Annulla")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun AddExerciseDialogPreview(){
    MyGymTheme {
        AddExerciseDialog()
    }
}