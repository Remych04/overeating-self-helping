package com.remych04.overeating.self.helping.base.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = tableName)
data class MealEntity(
    @PrimaryKey
    val id: Long,
    val meal: String,
    val feelings: String,
    val date: Long,
    val unnecessary: Boolean,
    val replacement: Boolean
)