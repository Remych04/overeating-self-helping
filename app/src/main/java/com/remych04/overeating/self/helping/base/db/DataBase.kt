package com.remych04.overeating.self.helping.base.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MealEntity::class], version = 1)
abstract class DataBase : RoomDatabase() {
    abstract fun mealDao(): MealDao
}