package com.remych04.overeating.self.helping.feature.addnew.presentation

import android.os.Bundle
import android.view.View
import com.remych04.overeating.self.helping.R
import com.remych04.overeating.self.helping.base.BaseFragment
import com.remych04.overeating.self.helping.base.ext.binding
import com.remych04.overeating.self.helping.data.MealDto
import com.remych04.overeating.self.helping.databinding.NewMealFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class NewMealFragment : BaseFragment(R.layout.daymeallist_fragment) {

    private val bind by binding { NewMealFragmentBinding.bind(this.requireView()) }

    private val model by viewModel<NewMealViewModel>()

    override fun getViewModel() = model

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(resources.getString(R.string.new_meal_fragment_title))
        bind.addNewMealButton.setOnClickListener {
            model.addNewMeal(
                MealDto(
                    meal = bind.mealEditText.text.toString(),
                    feelings = bind.feelingsEditText.text.toString(),
                    date = Calendar.getInstance().timeInMillis,
                    unnecessary = bind.isUnnecessary.isChecked,
                    replacement = bind.isReplacement.isChecked
                )
            )
        }
    }

    companion object {
        fun getInstance() = NewMealFragment()
    }
}
