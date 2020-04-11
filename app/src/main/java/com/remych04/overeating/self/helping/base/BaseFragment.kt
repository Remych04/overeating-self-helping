package com.remych04.overeating.self.helping.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.remych04.overeating.self.helping.MainActivity


abstract class BaseFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    protected fun setTitle(title: String, isBackButtonEnabled: Boolean = true) {
        val mainActivity = (activity as MainActivity)
        mainActivity.supportActionBar?.title = title
        mainActivity.supportActionBar?.setDisplayHomeAsUpEnabled(isBackButtonEnabled)
        if (isBackButtonEnabled) {
            mainActivity.setToolbarBackAction { getViewModel().toolbarBackClick() }
        } else {
            mainActivity.setToolbarBackAction(null)
        }
    }

    abstract fun getViewModel(): ToolbarBackNavigation
}