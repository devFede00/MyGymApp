package com.example.mygym.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface GymDao {

    @Query("SELECT * FROM `Plan`")
    fun getAllPlans():Flow<List<PlanWithExercises>>

    @Query("SELECT * FROM `Plan` WHERE planId=:planId")
    fun getPlanWithExerciseById(planId: Int):Flow<PlanWithExercises>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewPlan(newPlan: Plan)

    @Delete
    suspend fun deletePlan(plan: Plan)

    //----------------------------------------------------

    @Query("SELECT * FROM 'Exercise' WHERE planId=:planId")
    fun getAllExercisesOfPlan(planId:Int): Flow<List<Exercise>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewExercise(newExercise: Exercise)

    @Delete
    suspend fun deleteExercise(exercise: Exercise)

}