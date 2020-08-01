package com.remych04.overeating.self.helping.feature.daylist.data

import com.remych04.overeating.self.helping.base.db.meal.MealDao
import com.remych04.overeating.self.helping.base.db.meal.MealEntity
import com.remych04.overeating.self.helping.data.MealDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.ZoneId

class DayListRepository(private val mealDao: MealDao) {

    suspend fun getAll(): List<MealDto> = withContext(Dispatchers.IO) {
        val mealEntityList = mealDao.getAllMeals()
        return@withContext mapMealFromEntity(mealEntityList)
    }

    suspend fun getTodayList(): List<MealDto> = withContext(Dispatchers.IO) {
        val startOfDay = LocalDate.now()
            .atStartOfDay(ZoneId.systemDefault())
            .toInstant()
            .toEpochMilli()
        val nextDay = LocalDate.now()
            .plusDays(1)
            .atStartOfDay(ZoneId.systemDefault())
            .toInstant()
            .toEpochMilli()
        val mealEntity = mealDao.getOneDayMeals(startOfDay, nextDay)
        return@withContext mapMealFromEntity(mealEntity)
    }

    suspend fun getAllPlaces(): List<String> = withContext(Dispatchers.IO) {
        return@withContext mealDao.getAllLocations()
    }

    private fun mapMealFromEntity(daoList: List<MealEntity>): List<MealDto> {
        return daoList.map {
            MealDto(
                it.meal,
                it.feelings,
                it.location,
                it.date,
                it.unnecessary,
                it.replacement
            )
        }
    }
}