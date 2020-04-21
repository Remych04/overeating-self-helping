package com.remych04.overeating.self.helping.feature.daylist.presentation

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.remych04.overeating.self.helping.R
import com.remych04.overeating.self.helping.base.BaseFragment
import com.remych04.overeating.self.helping.base.ext.binding
import com.remych04.overeating.self.helping.databinding.DaymeallistFragmentBinding
import com.remych04.overeating.self.helping.feature.daylist.presentation.adapter.MealListAdapter
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
    }

    private fun initAdapter() {
        adapter = MealListAdapter()
        bind.mealList.layoutManager = LinearLayoutManager(context)
        bind.mealList.adapter = adapter
    }

    companion object {
        fun getInstance() = DayListFragment()
    }
}