package com.example.mygym.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mygym.GymManagerViewModel
import com.example.mygym.database.Plan
import com.example.mygym.database.PlanWithExercises
import com.example.mygym.ui.theme.MyGymTheme

private const val TAG = "MainScreen"

@Composable
fun MyApp(viewModel: GymManagerViewModel) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "plan_list") {
        composable("plan_list") {
            PlanListScreen(navController = navController, viewModel = viewModel)
        }
        composable("plan_details/{planId}") { backStackEntry ->
            val planId = backStackEntry.arguments?.getString("planId")?.toInt()!!
            PlanDetailsScreen(navController, viewModel, planId)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview(){
    MyGymTheme{
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ){
            PlanList(
                plansWithExercises = listOf(
                    PlanWithExercises(Plan(planId = 0,title = "preview0"), emptyList()),
                    PlanWithExercises(Plan(planId = 0,title = "preview1"), emptyList()),
                    PlanWithExercises(Plan(planId = 0,title = "preview0"), emptyList()),
                    PlanWithExercises(Plan(planId = 0,title = "preview1"), emptyList()),
                    PlanWithExercises(Plan(planId = 0,title = "preview0"), emptyList()),
                    PlanWithExercises(Plan(planId = 0,title = "preview1"), emptyList()),
                ),
                onPlanClick = {},
                onDeletePlan = {}
            )
        }
    }
}
