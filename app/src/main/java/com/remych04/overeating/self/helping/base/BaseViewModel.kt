package com.remych04.overeating.self.helping.base

import androidx.lifecycle.ViewModel
import ru.terrakok.cicerone.Router

abstract class BaseViewModel(private val router: Router) : ViewModel() {

    fun toolbarBackClick() {
        router.exit()
    }
}