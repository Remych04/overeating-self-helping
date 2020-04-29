package com.remych04.overeating.self.helping.feature.daylist.presentation.listerers

import androidx.recyclerview.widget.RecyclerView

abstract class HideShowViewsRecyclerScrollListener : RecyclerView.OnScrollListener() {

    private val thresholdScroll = 20
    private var scrolledDistance = 0
    private var controlsVisible = true

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        if (scrolledDistance > thresholdScroll && controlsVisible) {
            onHide();
            controlsVisible = false;
            scrolledDistance = 0;
        } else if (scrolledDistance < -thresholdScroll && !controlsVisible) {
            onShow();
            controlsVisible = true;
            scrolledDistance = 0;
        }

        if ((controlsVisible && dy > 0) || (!controlsVisible && dy < 0)) {
            scrolledDistance += dy;
        }
    }

    abstract fun onHide()
    abstract fun onShow()
}