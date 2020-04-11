package com.remych04.overeating.self.helping.feature.daylist.module

import com.remych04.overeating.self.helping.feature.daylist.data.DayListRepository
import com.remych04.overeating.self.helping.feature.daylist.presentation.DayListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dayListModule = module {
    single { DayListRepository(get()) }
    viewModel { DayListViewModel(get(), get()) }
}