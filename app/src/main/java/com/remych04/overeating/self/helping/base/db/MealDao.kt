package com.remych04.overeating.self.helping.base.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MealDao {

    @Query("SELECT * FROM $tableName")
    suspend fun getAllMeals(): List<MealEntity>

    @Query("SELECT * FROM $tableName WHERE date BETWEEN :dayStart and :dayEnd")
    suspend fun getOneDayMeals(dayStart: Long, dayEnd: Long): List<MealEntity>

    @Insert
    suspend fun insertMeal(mealEntity: MealEntity)
}