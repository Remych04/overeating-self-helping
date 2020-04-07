package com.remych04.overeating.self.helping.feature.daylist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.remych04.overeating.self.helping.Screens
import com.remych04.overeating.self.helping.base.BaseViewModel
import com.remych04.overeating.self.helping.base.db.MealDao
import com.remych04.overeating.self.helping.data.MealDto
import kotlinx.coroutines.launch
import ru.terrakok.cicerone.Router

class DayListViewModel(
    private val mealDao: MealDao,
    private val router: Router
) : BaseViewModel() {

    private val uiData = MutableLiveData<List<MealDto>>()

    init {
        loadMealList()
    }

    fun loadMealList() {
        viewModelScope.launch {
            val mealEntity = mealDao.getAllMeals()
            uiData.value = mealEntity.map {
                MealDto(
                    it.meal,
                    it.feelings,
                    it.date,
                    it.unnecessary,
                    it.replacement
                )
            }
        }
    }

    fun addMealClick() {
        router.navigateTo(Screens.NewMealFragmentScreen())
    }

    fun getData(): LiveData<List<MealDto>> {
        return uiData
    }
}