package com.remych04.overeating.self.helping.feature.daylist.presentation

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.remych04.overeating.self.helping.R
import com.remych04.overeating.self.helping.base.ext.binding
import com.remych04.overeating.self.helping.base.ext.setMainToolbar
import com.remych04.overeating.self.helping.databinding.DaymeallistFragmentBinding
import com.remych04.overeating.self.helping.feature.daylist.presentation.adapter.MealListAdapter
import com.remych04.overeating.self.helping.feature.daylist.presentation.listerers.HideShowViewsRecyclerScrollListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class DayListFragment : Fragment(R.layout.daymeallist_fragment) {

    private val bind by binding { DaymeallistFragmentBinding.bind(this.requireView()) }
    private val model by viewModel<DayListViewModel>()

    //TODO придумать экстеншн для автоматического обнулления полей
    private var adapter: MealListAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setMainToolbar(resources.getString(R.string.day_list_fragment_title))
        initAdapter()
        initScrollRecycler()

        bind.refreshMealList.setOnRefreshListener {
            model.loadMealList()
        }
        bind.addMealButton.setOnClickListener {
            model.addMealClick()
        }
        model.getData().observe(viewLifecycleOwner, Observer {
            bind.refreshMealList.isRefreshing = false
            adapter?.setList(it)
        })
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

    override fun onDestroyView() {
        //TODO I Love LifeCycle
        adapter = null
        super.onDestroyView()
    }

    companion object {
        fun getInstance() = DayListFragment()
    }
}