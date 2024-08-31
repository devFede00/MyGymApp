package com.example.mygym.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
        entities = [Exercise::class, Plan::class],
        version = 1,
        exportSchema = false
)
abstract class GymDatabase : RoomDatabase() {

    abstract fun gymDao(): GymDao

}