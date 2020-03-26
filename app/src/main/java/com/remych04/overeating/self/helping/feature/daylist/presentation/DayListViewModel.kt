package com.remych04.overeating.self.helping.feature.daylist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.remych04.overeating.self.helping.Screens
import com.remych04.overeating.self.helping.base.db.MealDao
import com.remych04.overeating.self.helping.data.MealDto
import ru.terrakok.cicerone.Router

class DayListViewModel(
    private val mealDao: MealDao,
    private val router: Router
) : ViewModel() {

    private val uiData = MutableLiveData<List<MealDto>>()

    init {

    }

    fun addMealClick(){
        router.navigateTo(Screens.NewMealFragmentScreen())
    }

    fun getData(): LiveData<List<MealDto>> {
        return uiData
    }
}