package com.remych04.overeating.self.helping

import android.app.Application
import com.remych04.overeating.self.helping.feature.daylist.module.dayListModule
import com.remych04.overeating.self.helping.modules.NavigationComponentModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val navigationModule = NavigationComponentModule()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            androidFileProperties()
            modules(navigationModule.getModule(), dayListModule)
        }
    }
}