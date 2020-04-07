package com.remych04.overeating.self.helping.feature.addnew.presentation

import androidx.lifecycle.viewModelScope
import com.remych04.overeating.self.helping.Screens
import com.remych04.overeating.self.helping.base.BaseViewModel
import com.remych04.overeating.self.helping.base.db.MealDao
import com.remych04.overeating.self.helping.base.db.MealEntity
import com.remych04.overeating.self.helping.data.MealDto
import kotlinx.coroutines.launch
import ru.terrakok.cicerone.Router
import java.util.*

class NewMealViewModel(
    private val mealDao: MealDao,
    private val router: Router
) : BaseViewModel() {

    private val currentTime: Calendar = Calendar.getInstance()

    fun addNewMeal(mealDto: MealDto) {
        val mealEntity = MealEntity(
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

    override fun toolbarBackClick() {
        router.backTo(Screens.DayListFragmentScreen())
    }
}