package com.example.mygym.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mygym.GymManagerViewModel
import com.example.mygym.R
import com.example.mygym.database.Plan
import com.example.mygym.database.PlanWithExercises
import com.example.mygym.ui.components.dialogs.AddPlanDialog
import com.example.mygym.ui.components.cards.PlanCard
import com.example.mygym.ui.theme.MyGymTheme


@Composable
fun PlanListScreen(navController: NavController, viewModel: GymManagerViewModel){
    val planWithExercises by viewModel.planWithExercises.collectAsState(initial = emptyList())

    var showAddPlanDialog by remember { mutableStateOf(false) }

    if (showAddPlanDialog) {
        AddPlanDialog(
            onConfirm = {
                viewModel.addPlan(it)
                showAddPlanDialog = false
            },
            onDismiss = { showAddPlanDialog = false }
        )
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 40.dp, horizontal = 8.dp)
    ){
        Text(text = "Schede", fontSize = 48.sp, fontWeight = FontWeight.Bold)
        PlanList(
            modifier = Modifier.weight(1F),
            plansWithExercises = planWithExercises,
            onPlanClick ={ planId:Int ->
                navController.navigate("plan_details/$planId") },
            onDeletePlan = viewModel::deletePlan
        )
        Spacer(modifier = Modifier.padding(vertical = 16.dp))
        AddPlanCard(onCreatePlan = { showAddPlanDialog = true })
    }

}


@Composable
fun PlanList(
    modifier: Modifier = Modifier,
    plansWithExercises:List<PlanWithExercises>,
    onPlanClick: (Int) -> Unit,
    onDeletePlan: (Int) -> Unit
){

        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier.padding(horizontal = 8.dp),
        ) {
            items(plansWithExercises) { planWithDetails ->

                PlanCard(Modifier.padding(8.dp),onPlanClick,onDeletePlan,planWithDetails.plan)
            }
        }
}



@Composable
fun AddPlanCard(onCreatePlan:()->Unit){
    OutlinedCard(
        border = BorderStroke(2.dp, Color.Gray),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        onClick = onCreatePlan,
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
                text = "Aggiungi nuova scheda"
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlanListPreview(){
    MyGymTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background, // Usa il background del tema
            ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 40.dp, horizontal = 8.dp)
            ) {
                Text(text = "Schede", fontSize = 48.sp, fontWeight = FontWeight.Bold)
                PlanList(
                    modifier = Modifier.weight(1F),
                    onPlanClick = {},
                    onDeletePlan = {},
                    plansWithExercises = listOf(
                        PlanWithExercises(
                            Plan(planId = 0, title = "preview"),
                            emptyList()
                        ),
                        PlanWithExercises(
                            Plan(planId = 0, title = "preview"),
                            emptyList()
                        )

                    )
                )
                Spacer(modifier = Modifier.padding(vertical = 16.dp))
                AddPlanCard(onCreatePlan = { })
            }
        }

    }
}