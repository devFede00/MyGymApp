package com.example.mygym.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
        entities = [Exercise::class, Plan::class],
        version = 2,
        exportSchema = false
)
abstract class GymDatabase : RoomDatabase() {

    abstract fun gymDao(): GymDao

}

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        // Esegui le query SQL necessarie per modificare la struttura del database
        db.execSQL("ALTER TABLE exercise ADD COLUMN description TEXT NOT NULL DEFAULT ''")
    }
}
