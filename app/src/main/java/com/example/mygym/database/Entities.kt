package com.example.mygym.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity
data class Exercise(
    @PrimaryKey(autoGenerate = true) val exerciseId: Int = 0,
    val name:String,
    val series:String,
    val repetitions:String,
    val recovery: String,
    val planId:Int
)

@Entity
data class Plan(
    @PrimaryKey(autoGenerate = true) val planId: Int = 0,
    var title:String
)

data class PlanWithExercises(
    @Embedded val plan: Plan,
    @Relation(
        parentColumn = "planId",
        entityColumn = "planId"
    )
    val exercises: List<Exercise>
)