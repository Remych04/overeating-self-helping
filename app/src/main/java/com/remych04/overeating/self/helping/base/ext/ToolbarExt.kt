package com.remych04.overeating.self.helping.base.ext

import androidx.fragment.app.Fragment
import com.remych04.overeating.self.helping.MainActivity
import com.remych04.overeating.self.helping.base.BaseViewModel

fun Fragment.setToolbarBackNavigation(title: String, viewModel: BaseViewModel) {
    with((activity as MainActivity)) {
        supportActionBar?.title = title
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setToolbarBackAction { viewModel.toolbarBackClick() }
    }
}

fun Fragment.setMainToolbar(title: String) {
    with((activity as MainActivity)) {
        supportActionBar?.title = title
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        setToolbarBackAction(null)
    }
}
