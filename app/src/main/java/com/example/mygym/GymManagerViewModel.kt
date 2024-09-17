package com.example.mygym

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mygym.database.Exercise
import com.example.mygym.database.GymDao
import com.example.mygym.database.Plan
import com.example.mygym.database.PlanWithExercises
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class GymManagerViewModel(private val gymDao: GymDao) : ViewModel() {

    val planWithExercises: Flow<List<PlanWithExercises>> = gymDao.getAllPlans()

    fun getPlanWithExercises(planId: Int): Flow<PlanWithExercises> {
        return gymDao.getPlanWithExerciseById(planId)
    }

    fun addPlan(title: String) {
        viewModelScope.launch {
            gymDao.insertNewPlan(Plan(title = title))
        }
    }

    fun deletePlan(planId: Int) {
        viewModelScope.launch {
            val planWithExercises = gymDao.getPlanWithExerciseById(planId).first()
            val exercises = planWithExercises.exercises
            val plan = planWithExercises.plan

            exercises.forEach {
                gymDao.deleteExercise(it)
            }

            gymDao.deletePlan(plan)

        }
    }

    fun addExercise(
        name: String,
        series: String,
        repetitions: String,
        recovery: String,
        planId: Int,
        description: String
    ) {
        viewModelScope.launch {
            gymDao.insertNewExercise(
                Exercise(
                    name = name,
                    series = series,
                    repetitions = repetitions,
                    recovery = recovery,
                    planId = planId,
                    description = description
                )
            )
        }
    }

    fun deleteExercise(exerciseToDelete: Exercise) {
        viewModelScope.launch {
            gymDao.deleteExercise(exerciseToDelete)
        }
    }

}