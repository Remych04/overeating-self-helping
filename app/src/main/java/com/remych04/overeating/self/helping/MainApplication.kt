package com.remych04.overeating.self.helping

import android.app.Application
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.databases.DatabasesFlipperPlugin
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.soloader.SoLoader
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

        initFlipper()
    }

    private fun initFlipper() {
        SoLoader.init(this, false)
        if (BuildConfig.DEBUG && FlipperUtils.shouldEnableFlipper(this)) {
            val client = AndroidFlipperClient.getInstance(this)
            client.addPlugin(InspectorFlipperPlugin(this, DescriptorMapping.withDefaults()))
            client.addPlugin(DatabasesFlipperPlugin(this));
            client.start()
        }
    }
}