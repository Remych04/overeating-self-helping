package com.remych04.overeating.self.helping.data

import com.remych04.overeating.self.helping.base.db.meal.MealDao
import com.remych04.overeating.self.helping.base.db.meal.MealEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DayListRepository(private val mealDao: MealDao) {

    suspend fun getAll(): List<MealDto> = withContext(Dispatchers.IO) {
        val mealEntityList = mealDao.getAllMeals()
        return@withContext mapMealFromEntity(mealEntityList)
    }

    suspend fun getDayList(startOfDay: Long, nextDay: Long): List<MealDto> {
        val mealEntity = mealDao.getOneDayMeals(startOfDay, nextDay - 1)
        return mapMealFromEntity(mealEntity)
    }

    suspend fun getAllPlaces(): List<String> {
        return mealDao.getAllLocations()
    }

    suspend fun insertNewMeal(mealItem: MealDto) {
        mealDao.insertMeal(mapDtoToEntity(mealItem))
    }

    suspend fun removeItem(mealItem: MealDto) = withContext(Dispatchers.IO) {
        mealItem.id?.let {
            mealDao.removeMeal(it)
        } ?: return@withContext false
        return@withContext true
    }

    suspend fun changeItem(mealItem: MealDto) = withContext(Dispatchers.IO) {
        mealDao.changeMeal(mapDtoToEntity(mealItem))
    }

    private fun mapDtoToEntity(mealDto: MealDto): MealEntity {
        return MealEntity(
            id = mealDto.id,
            meal = mealDto.meal,
            feelings = mealDto.feelings,
            location = mealDto.location,
            date = mealDto.date,
            unnecessary = mealDto.unnecessary,
            replacement = mealDto.replacement
        )
    }

    private fun mapMealFromEntity(daoList: List<MealEntity>): List<MealDto> {
        return daoList.map {
            MealDto(
                it.id,
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