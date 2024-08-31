package com.example.mygym.ui.components.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mygym.R
import com.example.mygym.database.Exercise

import com.example.mygym.ui.theme.MyGymTheme

@Composable
fun ExerciseCard(
    modifier: Modifier = Modifier,
    exercise: Exercise
){
    Card(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(), // Assicura che il Row occupi tutta la larghezza disponibile
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = R.drawable.baseline_list_alt_24),
                contentDescription = null,
                modifier = Modifier.size(90.dp)
            )
            Column {
                Row {
                    Text(
                        text = "Esercizio: ",
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = exercise.name,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                Row {
                    Text(
                        text = "Ripetizioni: ",
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "${exercise.series}x${exercise.repetitions}",
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                Row {
                    Text(
                        text = "Recupero: ",
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "${exercise.recovery}",
                        color = MaterialTheme.colorScheme.primary
                    )
                }

            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExerciseCardPreview(){
    MyGymTheme {
        ExerciseCard(exercise = Exercise(name = "Flessioni", series ="2", repetitions = "10", recovery = "60", planId = 0))
    }
}