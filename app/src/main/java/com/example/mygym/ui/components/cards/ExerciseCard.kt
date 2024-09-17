package com.example.mygym.ui.components.cards

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mygym.R
import com.example.mygym.database.Exercise
import com.example.mygym.ui.components.dialogs.DescriptionExerciseDialog

import com.example.mygym.ui.theme.MyGymTheme

val fontSize = 16.sp

@Composable
fun ExerciseCard(
    modifier: Modifier = Modifier,
    onDeleteExercise: (Exercise) -> Unit,
    exercise: Exercise
) {
    var showDescriptionExerciseDialog by remember { mutableStateOf(false) }
    var showDeleteExerciseDialog by remember { mutableStateOf(false) }

    if (showDescriptionExerciseDialog) {
        DescriptionExerciseDialog(
            onDismiss = { showDescriptionExerciseDialog = false },
            description = exercise.description
        )
    }

    if (showDeleteExerciseDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteExerciseDialog = false },
            confirmButton = {
                OutlinedButton(
                    onClick = {
                        onDeleteExercise(exercise)
                        showDeleteExerciseDialog = false
                              },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.background,
                        contentColor = MaterialTheme.colorScheme.primary
                    )
                ){
                    Text(text = "Si, elimina")
                }
            },
            dismissButton = {
                Button(
                    onClick = { showDeleteExerciseDialog = false },
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text("Annulla")
                }
            },
            text = {
                Text(text = "Sicuro di voler rimuovere l'esercizio '${exercise.name}' ?")
            }
        )
    }


    Row{
        Card(
            modifier = modifier
                .fillMaxWidth()
            ,
            border = BorderStroke(2.dp, MaterialTheme.colorScheme.onBackground),
            onClick = {
                showDescriptionExerciseDialog = true
            },
            colors = CardDefaults.cardColors(
                contentColor = MaterialTheme.colorScheme.onBackground,
                containerColor = Color.Transparent,
            ),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(), // Assicura che il Row occupi tutta la larghezza disponibile
                verticalAlignment = Alignment.CenterVertically
            ) {
//            Icon(
//                painter = painterResource(id = R.drawable.baseline_list_alt_24),
//                contentDescription = null,
//                modifier = Modifier.size(90.dp),
//            )
                Column(
                    modifier = Modifier
                        .weight(0.7f)
                        .padding(horizontal = 12.dp)
                ) {
                    Row {
//                    Text(
//                        text = "Esercizio: ",
//                        fontWeight = FontWeight.Bold,
//                        fontSize = 16.sp
//                    )
                        Text(
                            text = exercise.name,
                        )
                    }
                    Row {
//                    Text(
//                        text = "Ripetizioni: ",
//                        fontWeight = FontWeight.Bold
//                    )
                        Text(
                            text = "${exercise.series}x${exercise.repetitions}",
                        )
                    }
                    Row {
//                    Text(
//                        text = "Recupero: ",
//                        fontWeight = FontWeight.Bold
//                    )
                        Text(
                            text = "${exercise.recovery} s",
                        )
                    }

                }
            Button(
                onClick = {showDeleteExerciseDialog = true},
                modifier= Modifier.size(90.dp),
                shape = ShapeDefaults.ExtraSmall,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red
                )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_delete_24),
                    contentDescription = null,
                    modifier = Modifier.size(35.dp)
                )
            }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExerciseCardPreview() {
    MyGymTheme {
        ExerciseCard(
            exercise = Exercise(
                name = "Flessioni",
                series = "2",
                repetitions = "10",
                recovery = "60",
                planId = 0
            ),
            onDeleteExercise = {}
        )
    }
}