package com.remych04.overeating.self.helping

import android.app.Application
import com.remych04.overeating.self.helping.base.modules.DataBaseModule
import com.remych04.overeating.self.helping.base.modules.NavigationComponentModule
import com.remych04.overeating.self.helping.feature.addnew.module.newMealModule
import com.remych04.overeating.self.helping.feature.daylist.module.dayListModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val navigationModule =
            NavigationComponentModule()
        val dbModule =
            DataBaseModule(this)

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            androidFileProperties()
            modules(
                navigationModule.getModule(),
                dbModule.getModule(),
                dayListModule,
                newMealModule
            )
        }
    }
}