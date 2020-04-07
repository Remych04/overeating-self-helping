package com.remych04.overeating.self.helping.base

import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel(), ToolbarBackNavigation {

    override fun toolbarBackClick() {
    }
}