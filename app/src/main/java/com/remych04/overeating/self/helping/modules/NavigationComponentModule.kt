package com.remych04.overeating.self.helping.modules

import org.koin.dsl.module
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

class NavigationComponentModule {

    private val cicerone: Cicerone<Router> = Cicerone.create()

    fun getModule() = module {
        single { getNavigatorHolder() }
        single { getRouter() }
    }

    private fun getNavigatorHolder() = cicerone.navigatorHolder

    private fun getRouter() = cicerone.router
}


