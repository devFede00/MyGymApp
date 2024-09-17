package com.example.mygym.ui.screens

import android.graphics.Color.rgb
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import com.example.mygym.GymManagerViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.alpha
import com.example.mygym.R
import com.example.mygym.database.Exercise
import com.example.mygym.database.Plan
import com.example.mygym.database.PlanWithExercises
import com.example.mygym.ui.components.dialogs.AddExerciseDialog
import com.example.mygym.ui.components.cards.ExerciseCard
import com.example.mygym.ui.components.dialogs.DescriptionExerciseDialog
import com.example.mygym.ui.theme.MyGymTheme

@Composable
fun PlanDetailsScreen(
    navController: NavHostController,
    viewModel: GymManagerViewModel,
    planId: Int
) {
    // Supponiamo di ottenere la categoria e gli item dal ViewModel
    val planWithExercises by viewModel.getPlanWithExercises(planId).collectAsState(initial = null)

    var showAddExerciseDialog by remember { mutableStateOf(false) }

    if (showAddExerciseDialog) {
        AddExerciseDialog(
            onConfirm = { name, series, repetitions, recovery, description ->
                viewModel.addExercise(name, series, repetitions, recovery, planId, description)
                showAddExerciseDialog = false
            },
            onDismiss = { showAddExerciseDialog = false }
        )
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        planWithExercises?.let {
            PlanDetails(
                planWithExercises = it,
                onAddExercise = { showAddExerciseDialog = true },
                onDeleteExercise = viewModel::deleteExercise
            )

        } ?: run {
            // Gestione del caso in cui la categoria non esista
            Text(text = "Scheda non trovata")
        }
    }
}

@Composable
fun PlanDetails(planWithExercises: PlanWithExercises, onAddExercise: () -> Unit, onDeleteExercise:(Exercise)->Unit) {

    val exercises = planWithExercises.exercises

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 40.dp, horizontal = 8.dp)
    ) {
        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            ),
            colors = CardDefaults.elevatedCardColors(
                contentColor = Color.White,
                containerColor = Color.Black
            )
        ) {
            Text(
                text = planWithExercises.plan.title,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
        Spacer(modifier = Modifier.padding(vertical = 20.dp))
        if (exercises.isNotEmpty()) {
            ExerciseList(
                modifier = Modifier.weight(1F),
                onDeleteExercise = onDeleteExercise,
                exerciseList = exercises,
            )
        }
        Spacer(modifier = Modifier.padding(vertical = 20.dp))
        AddExerciseCard(onAddExercise = onAddExercise)
    }
}

@Composable
fun ExerciseList(
    exerciseList: List<Exercise>,
    onDeleteExercise: (Exercise) -> Unit,
    modifier: Modifier = Modifier,
) {

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.padding(horizontal = 8.dp)
    ) {
        items(exerciseList) { exercise ->

            ExerciseCard(
                modifier = Modifier.padding(8.dp),
                onDeleteExercise = onDeleteExercise,
                exercise = exercise
            )

        }
    }

}

@Composable
fun AddExerciseCard(
    modifier: Modifier = Modifier,
    onAddExercise: () -> Unit
) {
    OutlinedCard(
        border = BorderStroke(2.dp, Color.Gray),
        modifier = modifier
            .padding(horizontal = 16.dp),
        onClick = onAddExercise
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(), // Assicura che il Row occupi tutta la larghezza disponibile
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Image(
                painter = painterResource(id = R.drawable.baseline_add_24),
                contentDescription = null,
                Modifier.size(60.dp)
            )
            Text(
                text = "Aggiungi nuovo esercizio"
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlanDetailsPreview() {
    MyGymTheme {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize()
        ) {
            PlanDetails(
                planWithExercises =
                PlanWithExercises(
                    Plan(planId = 0, title = "preview"),
                    listOf(
                        Exercise(
                            name = "nome",
                            series = "2",
                            repetitions = "2",
                            recovery = "recovery",
                            planId = 0
                        ),
                        Exercise(
                            name = "nome",
                            series = "2",
                            repetitions = "2",
                            recovery = "recovery",
                            planId = 0
                        ),
                        Exercise(
                            name = "nome",
                            series = "2",
                            repetitions = "2",
                            recovery = "recovery",
                            planId = 0
                        ),
                        Exercise(
                            name = "nome",
                            series = "2",
                            repetitions = "2",
                            recovery = "recovery",
                            planId = 0
                        ),
                        Exercise(
                            name = "nome",
                            series = "2",
                            repetitions = "2",
                            recovery = "recovery",
                            planId = 0
                        ),
                        Exercise(
                            name = "nome",
                            series = "2",
                            repetitions = "2",
                            recovery = "recovery",
                            planId = 0
                        ),
                        Exercise(
                            name = "nome",
                            series = "2",
                            repetitions = "2",
                            recovery = "recovery",
                            planId = 0
                        ),
                        Exercise(
                            name = "nome",
                            series = "2",
                            repetitions = "2",
                            recovery = "recovery",
                            planId = 0
                        ),
                    )
                ),
                onAddExercise = {},
                onDeleteExercise = {}
            )

        }
    }
}