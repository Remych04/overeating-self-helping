package com.remych04.overeating.self.helping.feature.daylist.presentation

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textview.MaterialTextView
import com.remych04.overeating.self.helping.R
import com.remych04.overeating.self.helping.base.ext.setMainToolbar
import com.remych04.overeating.self.helping.feature.daylist.presentation.adapter.MealListAdapter
import com.remych04.overeating.self.helping.feature.daylist.presentation.adapter.SpinnerEvent
import com.remych04.overeating.self.helping.feature.daylist.presentation.listerers.HideShowViewsRecyclerScrollListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class DayListFragment : Fragment(R.layout.daymeallist_fragment) {

    private val model by viewModel<DayListViewModel>()

    //TODO придумать экстеншн для автоматического обнулления полей
    private var adapter: MealListAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val refreshMealList = view.findViewById<SwipeRefreshLayout>(R.id.refresh_mealList)
        val mealList = view.findViewById<RecyclerView>(R.id.mealList)
        val addMealButton = view.findViewById<FloatingActionButton>(R.id.add_meal_button)
        val previousDateButton = view.findViewById<AppCompatImageButton>(R.id.previous_date_button)
        val chosenDate = view.findViewById<MaterialTextView>(R.id.chosen_date)
        val nextDateButton = view.findViewById<AppCompatImageButton>(R.id.next_date_button)
        setMainToolbar(resources.getString(R.string.day_list_fragment_title))
        initAdapter(mealList)
        initSliderDateClickListeners(previousDateButton, chosenDate, nextDateButton)
        initScrollRecycler(addMealButton, mealList)


        refreshMealList.setOnRefreshListener {
            model.loadMealList(chosenDate.text.toString())
        }
        addMealButton.setOnClickListener {
            model.addMealClick()
        }
        model.getData().observe(viewLifecycleOwner, Observer {
            refreshMealList.isRefreshing = false
            chosenDate.text = it.date
            adapter?.setList(it.mealList)
        })
    }

    private fun initAdapter(mealList: RecyclerView) {
        adapter = MealListAdapter { mealItem, event ->
            if (event == SpinnerEvent.CHANGE) {
                model.changeData(mealItem)
            }
        }
        mealList.layoutManager = LinearLayoutManager(context)
        mealList.adapter = adapter
    }

    private fun initSliderDateClickListeners(previousDateButton: AppCompatImageButton, chosenDate: MaterialTextView, nextDateButton: AppCompatImageButton) {
        previousDateButton.setOnClickListener {
            model.previousDateClick(chosenDate.text)
        }
        nextDateButton.setOnClickListener {
            model.nextDateClick(chosenDate.text)
        }
    }

    private fun initScrollRecycler(addMealButton: FloatingActionButton, mealList: RecyclerView) {
        mealList.addOnScrollListener(object : HideShowViewsRecyclerScrollListener() {

            override fun onHide() {
                val bottomMargin =
                    (addMealButton.layoutParams as ViewGroup.MarginLayoutParams).bottomMargin
                addMealButton.animate()
                    .translationY((addMealButton.height + bottomMargin).toFloat())
                    .setInterpolator(AccelerateInterpolator(2F))
                    .start();
            }

            override fun onShow() {
                addMealButton.animate()
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