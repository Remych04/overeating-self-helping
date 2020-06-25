package com.remych04.overeating.self.helping.feature.daylist.presentation

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.appcompat.widget.PopupMenu
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.remych04.overeating.self.helping.R
import com.remych04.overeating.self.helping.base.BaseFragment
import com.remych04.overeating.self.helping.base.ext.binding
import com.remych04.overeating.self.helping.databinding.DaymeallistFragmentBinding
import com.remych04.overeating.self.helping.feature.daylist.presentation.adapter.MealListAdapter
import com.remych04.overeating.self.helping.feature.daylist.presentation.listerers.HideShowViewsRecyclerScrollListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class DayListFragment : BaseFragment(R.layout.daymeallist_fragment) {

    private val bind by binding { DaymeallistFragmentBinding.bind(this.requireView()) }
    private val model by viewModel<DayListViewModel>()

    private lateinit var adapter: MealListAdapter

    override fun getViewModel() = model

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(resources.getString(R.string.day_list_fragment_title), false)
        initAdapter()
        initScrollRecycler()

        //TODO СДЕЛАТЬ НОРМ ДАТУ
        bind.todayDateText.text = "17 Августа"
        bind.refreshMealList.setOnRefreshListener {
            model.loadMealList()
        }
        bind.addMealButton.setOnClickListener {
            model.addMealClick()
        }
        model.getData().observe(viewLifecycleOwner, Observer {
            bind.refreshMealList.isRefreshing = false
            adapter.setList(it)
        })

        bind.popupbutton.setOnClickListener {
            val popupMenu = PopupMenu(requireContext(), it)
            popupMenu.inflate(R.menu.popup_menu)
            popupMenu.show()
        }
    }

    private fun initAdapter() {
        adapter = MealListAdapter()
        bind.mealList.layoutManager = LinearLayoutManager(context)
        bind.mealList.adapter = adapter
    }

    private fun initScrollRecycler() {
        bind.mealList.addOnScrollListener(object : HideShowViewsRecyclerScrollListener() {

            override fun onHide() {
                val fabButton = bind.addMealButton
                val bottomMargin =
                    (fabButton.layoutParams as ViewGroup.MarginLayoutParams).bottomMargin
                fabButton.animate()
                    .translationY((fabButton.height + bottomMargin).toFloat())
                    .setInterpolator(AccelerateInterpolator(2F))
                    .start();
            }

            override fun onShow() {
                bind.addMealButton.animate()
                    .translationY(0F)
                    .setInterpolator(DecelerateInterpolator(2F))
                    .start()
            }
        })
    }

    companion object {
        fun getInstance() = DayListFragment()
    }
}