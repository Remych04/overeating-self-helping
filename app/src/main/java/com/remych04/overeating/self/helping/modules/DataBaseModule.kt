package com.remych04.overeating.self.helping.modules

import android.app.Application
import androidx.room.Room
import com.remych04.overeating.self.helping.base.db.DataBase
import com.remych04.overeating.self.helping.base.db.MealDao
import org.koin.dsl.module

class DataBaseModule(private val application: Application) {

    private val dbBuilder = Room.databaseBuilder(
        application, DataBase::class.java, "mealDb"
    ).build()

    fun getModule() = module {
        single { getMealDao() }
    }

    private fun getMealDao(): MealDao {
        return dbBuilder.mealDao()
    }
}