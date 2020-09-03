package com.remych04.overeating.self.helping.data

import java.io.Serializable

data class MealDto(
    val id: Long?,
    val meal: String,
    val feelings: String,
    val location: String,
    val date: Long,
    val unnecessary: Boolean,
    val replacement: Boolean
) : Serializable