package com.remych04.overeating.self.helping.base.modules

import org.koin.dsl.module
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

val navigationModule = module {
    single { createCicerone() }
    single { getNavigatorHolder(get()) }
    single { getRouter(get()) }
}

private fun createCicerone(): Cicerone<Router> = Cicerone.create()

private fun getNavigatorHolder(cicerone: Cicerone<Router>) = cicerone.navigatorHolder

private fun getRouter(cicerone: Cicerone<Router>) = cicerone.router


