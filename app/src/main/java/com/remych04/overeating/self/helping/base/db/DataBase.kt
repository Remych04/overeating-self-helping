package com.remych04.overeating.self.helping.base.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.remych04.overeating.self.helping.base.db.meal.MealDao
import com.remych04.overeating.self.helping.base.db.meal.MealEntity

@Database(entities = [MealEntity::class], version = 1)
abstract class DataBase : RoomDatabase() {
    abstract fun mealDao(): MealDao
}