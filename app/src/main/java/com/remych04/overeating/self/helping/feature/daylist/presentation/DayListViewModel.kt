package com.remych04.overeating.self.helping.feature.daylist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.remych04.overeating.self.helping.Screens
import com.remych04.overeating.self.helping.base.BaseViewModel
import com.remych04.overeating.self.helping.base.ext.formattedDateToZonedDateTime
import com.remych04.overeating.self.helping.base.ext.getCurrentTime
import com.remych04.overeating.self.helping.base.ext.toFormattedDate
import com.remych04.overeating.self.helping.data.DayListRepository
import com.remych04.overeating.self.helping.data.MealDto
import com.remych04.overeating.self.helping.feature.daylist.data.DayListModel
import kotlinx.coroutines.launch
import ru.terrakok.cicerone.Router

class DayListViewModel(
    private val dayListRepository: DayListRepository,
    private val router: Router
) : BaseViewModel(router) {

    private val uiData = MutableLiveData<DayListModel>()

    init {
        loadMealList(getCurrentTime())
    }

    fun loadMealList(formatDate: String) {
        viewModelScope.launch {
            val startDay = formatDate.formattedDateToZonedDateTime()
            val endDay = startDay.plusDays(1).toInstant().toEpochMilli()
            val mealList = dayListRepository.getDayList(startDay.toInstant().toEpochMilli(), endDay)
            uiData.value = DayListModel(mealList, formatDate)
        }
    }

    fun addMealClick() {
        router.navigateTo(Screens.NewMealFragmentScreen())
    }

    fun getData(): LiveData<DayListModel> {
        return uiData
    }

    fun changeData(mealItem: MealDto) {
        router.navigateTo(Screens.NewMealFragmentScreenWithParams(mealItem))
    }

    fun previousDateClick(formatDate: CharSequence) {
        viewModelScope.launch {
            val startDay = formatDate.formattedDateToZonedDateTime().minusDays(1)
            val endDay = startDay.plusDays(1).toInstant().toEpochMilli()
            val mealList = dayListRepository.getDayList(startDay.toInstant().toEpochMilli(), endDay)
            uiData.value = DayListModel(mealList, startDay.toFormattedDate())
        }
    }

    fun nextDateClick(formatDate: CharSequence) {
        viewModelScope.launch {
            val startDay = formatDate.formattedDateToZonedDateTime().plusDays(1)
            val endDay = startDay.plusDays(1).toInstant().toEpochMilli()
            val mealList = dayListRepository.getDayList(startDay.toInstant().toEpochMilli(), endDay)
            uiData.value = DayListModel(mealList, startDay.toFormattedDate())
        }
    }
}