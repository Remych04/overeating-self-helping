package com.remych04.overeating.self.helping.feature.daylist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.remych04.overeating.self.helping.feature.daylist.data.MealDto

class DayListViewModel : ViewModel() {

    private val uiData = MutableLiveData<List<MealDto>>()

    init {
        uiData.value = listOf(MealDto("ПОНЧИКИ"), MealDto("ВАРЕНИКИ"))
    }

    fun getData(): LiveData<List<MealDto>> {
        return uiData
    }
}