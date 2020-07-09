package com.remych04.overeating.self.helping.feature.addnew.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.remych04.overeating.self.helping.Screens
import com.remych04.overeating.self.helping.base.BaseViewModel
import com.remych04.overeating.self.helping.base.db.meal.MealDao
import com.remych04.overeating.self.helping.base.db.meal.MealEntity
import com.remych04.overeating.self.helping.data.MealDto
import com.remych04.overeating.self.helping.feature.daylist.data.DayListRepository
import kotlinx.coroutines.launch
import ru.terrakok.cicerone.Router

class NewMealViewModel(
    private val dayListRepository: DayListRepository,
    private val mealDao: MealDao,
    private val router: Router
) : BaseViewModel() {

    private val uiData = MutableLiveData<List<String>>()

    init {
        loadAllLocations()
    }

    fun loadAllLocations() {
        viewModelScope.launch {
            uiData.value = dayListRepository.getAll().map { it.location }.distinct()
        }
    }

    fun addNewMeal(mealDto: MealDto) {
        val mealEntity =
            MealEntity(
                meal = mealDto.meal,
                feelings = mealDto.feelings,
                location = mealDto.location,
                date = mealDto.date,
                unnecessary = mealDto.unnecessary,
                replacement = mealDto.replacement
            )
        viewModelScope.launch {
            mealDao.insertMeal(mealEntity)
        }
    }

    fun getData(): LiveData<List<String>> = uiData

    override fun toolbarBackClick() {
        router.backTo(Screens.DayListFragmentScreen())
    }
}