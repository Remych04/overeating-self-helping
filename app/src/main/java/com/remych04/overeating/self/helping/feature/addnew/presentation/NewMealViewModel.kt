package com.remych04.overeating.self.helping.feature.addnew.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.remych04.overeating.self.helping.Screens
import com.remych04.overeating.self.helping.base.BaseViewModel
import com.remych04.overeating.self.helping.data.MealDto
import com.remych04.overeating.self.helping.feature.daylist.data.DayListRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import ru.terrakok.cicerone.Router

class NewMealViewModel(
    private val dayListRepository: DayListRepository,
    private val router: Router
) : BaseViewModel() {

    private val locationsData = MutableLiveData<List<String>>()
    private val insertSuccessData = MutableLiveData<Boolean>()

    init {
        loadAllLocations()
    }

    private fun loadAllLocations() {
        viewModelScope.launch {
            locationsData.value = dayListRepository.getAllPlaces()
        }
    }

    fun addNewMeal(mealDto: MealDto) {
        val exceptionHandler = CoroutineExceptionHandler { _, exception ->
            //TODO трекать ошибку
            insertSuccessData.value = false
        }
        viewModelScope.launch(exceptionHandler) {
            dayListRepository.insertNewMeal(mealDto)
            insertSuccessData.value = true
        }
    }

    fun getLocationsData(): LiveData<List<String>> = locationsData
    fun getSuccessInsertData(): LiveData<Boolean> = insertSuccessData

    override fun toolbarBackClick() {
        router.backTo(Screens.DayListFragmentScreen())
    }

    fun changeMeal(mealItem: MealDto) {
        viewModelScope.launch {
            dayListRepository.changeItem(mealItem)
        }
    }
}