package com.remych04.overeating.self.helping.feature.addnew.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.remych04.overeating.self.helping.base.db.MealDao
import com.remych04.overeating.self.helping.base.db.MealEntity
import com.remych04.overeating.self.helping.data.MealDto
import kotlinx.coroutines.launch
import java.util.*

class NewMealViewModel(
    private val mealDao: MealDao
) : ViewModel() {

    private val currentTime: Calendar = Calendar.getInstance()

    fun addNewMeal(mealDto: MealDto) {
        val mealEntity: MealEntity = MealEntity(
            meal = mealDto.meal,
            feelings = mealDto.feelings,
            date = currentTime.timeInMillis,
            unnecessary = mealDto.unnecessary,
            replacement = mealDto.replacement
        )
        viewModelScope.launch {
            mealDao.insertMeal(mealEntity)
        }
    }
}