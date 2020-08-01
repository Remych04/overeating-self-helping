package com.remych04.overeating.self.helping.base.db.meal

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.remych04.overeating.self.helping.base.db.mealTableName

@Dao
interface MealDao {

    @Query("SELECT * FROM $mealTableName")
    suspend fun getAllMeals(): List<MealEntity>

    @Query("SELECT * FROM $mealTableName WHERE date BETWEEN :dayStart and :dayEnd")
    suspend fun getOneDayMeals(dayStart: Long, dayEnd: Long): List<MealEntity>

    @Query("SELECT DISTINCT location FROM $mealTableName")
    suspend fun getAllLocations(): List<String>

    @Insert
    suspend fun insertMeal(mealEntity: MealEntity)
}