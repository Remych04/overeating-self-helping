package com.remych04.overeating.self.helping.base.modules

import android.content.Context
import androidx.room.Room
import com.remych04.overeating.self.helping.base.db.DataBase
import com.remych04.overeating.self.helping.base.db.meal.MealDao
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dbModule = module {
    single { getDb(androidContext()) }
    single { getMealDao(get()) }
}

private fun getDb(context: Context): DataBase {
    return Room.databaseBuilder(
        context, DataBase::class.java, "overeatingDb"
    ).build()
}

private fun getMealDao(dataBase: DataBase): MealDao {
    return dataBase.mealDao()
}