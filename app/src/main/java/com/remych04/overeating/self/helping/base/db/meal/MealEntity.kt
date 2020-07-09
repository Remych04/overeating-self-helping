package com.remych04.overeating.self.helping.base.db.meal

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.remych04.overeating.self.helping.base.db.mealTableName

@Entity(tableName = mealTableName)
data class MealEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val meal: String,
    val feelings: String,
    val location: String,
    val date: Long,
    val unnecessary: Boolean,
    val replacement: Boolean
)