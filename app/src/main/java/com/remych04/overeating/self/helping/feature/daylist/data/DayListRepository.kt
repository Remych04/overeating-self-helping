package com.remych04.overeating.self.helping.feature.daylist.data

import com.remych04.overeating.self.helping.base.db.DAY
import com.remych04.overeating.self.helping.base.db.MealDao
import com.remych04.overeating.self.helping.base.db.MealEntity
import com.remych04.overeating.self.helping.data.MealDto
import java.util.*

class DayListRepository(private val mealDao: MealDao) {

    suspend fun getAll(): List<MealDto> {
        val mealEntity = mealDao.getAllMeals()
        return mealEntity.map {
            MealDto(
                it.meal,
                it.feelings,
                it.date,
                it.unnecessary,
                it.replacement
            )
        }
    }

    suspend fun getTodayList(): List<MealDto> {
        val currentTime: Calendar = Calendar.getInstance()
        currentTime.set(Calendar.HOUR_OF_DAY, 0)
        currentTime.set(Calendar.MINUTE, 0);
        currentTime.set(Calendar.SECOND, 0);
        currentTime.set(Calendar.MILLISECOND, 0);
        val startOfDay = currentTime.timeInMillis
        val mealEntity = mealDao.getOneDayMeals(startOfDay, startOfDay + DAY)
        return mapMealFromEntity(mealEntity)
    }

    private fun mapMealFromEntity(daoList: List<MealEntity>): List<MealDto> {
        return daoList.map {
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