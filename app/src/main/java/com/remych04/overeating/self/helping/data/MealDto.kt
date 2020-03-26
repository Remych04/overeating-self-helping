package com.remych04.overeating.self.helping.data

data class MealDto(
    val meal: String,
    val feelings: String,
    val date: Long,
    val unnecessary: Boolean,
    val replacement: Boolean
)