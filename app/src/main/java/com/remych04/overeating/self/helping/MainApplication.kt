package com.remych04.overeating.self.helping

import android.app.Application
import com.remych04.overeating.self.helping.base.modules.dbModule
import com.remych04.overeating.self.helping.base.modules.navigationModule
import com.remych04.overeating.self.helping.feature.addnew.module.newMealModule
import com.remych04.overeating.self.helping.feature.daylist.module.dayListModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            androidFileProperties()
            modules(
                navigationModule,
                dbModule,
                dayListModule,
                newMealModule
            )
        }
    }
}