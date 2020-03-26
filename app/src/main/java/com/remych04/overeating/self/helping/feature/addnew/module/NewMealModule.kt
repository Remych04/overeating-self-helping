package com.remych04.overeating.self.helping.feature.addnew.module

import com.remych04.overeating.self.helping.feature.addnew.presentation.NewMealViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val newMealModule = module {
    viewModel { NewMealViewModel(get()) }
}