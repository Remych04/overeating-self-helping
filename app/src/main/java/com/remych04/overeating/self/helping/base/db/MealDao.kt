package com.remych04.overeating.self.helping.base.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MealDao {

    @Query("SELECT * FROM $tableName")

    @Insert
    fun insert(mealEntity: MealEntity)
}