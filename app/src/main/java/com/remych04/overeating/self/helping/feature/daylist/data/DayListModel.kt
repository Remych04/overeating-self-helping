package com.remych04.overeating.self.helping.feature.daylist.data

import com.remych04.overeating.self.helping.data.MealDto

data class DayListModel(
    val mealList: List<MealDto>,
    val date: String
)