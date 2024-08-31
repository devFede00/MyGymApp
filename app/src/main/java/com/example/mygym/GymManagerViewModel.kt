package com.example.mygym

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mygym.database.Exercise
import com.example.mygym.database.GymDao
import com.example.mygym.database.Plan
import com.example.mygym.database.PlanWithExercises
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class GymManagerViewModel(private val gymDao: GymDao) : ViewModel(){

    val planWithExercises : Flow<List<PlanWithExercises>> = gymDao.getAllPlans()

    fun getPlanWithExercises(planId: Int):Flow<PlanWithExercises>{
        return gymDao.getPlanById(planId)
    }

    fun addPlan(title:String) {
        viewModelScope.launch {
            gymDao.insertNewPlan(Plan(title = title))
        }
    }

    fun addExercise(name:String, series:String, repetitions:String, recovery: String, planId:Int) {
        viewModelScope.launch {
            gymDao.insertNewExercise(Exercise(name = name, series = series, repetitions = repetitions, recovery = recovery, planId = planId))
        }
    }

}