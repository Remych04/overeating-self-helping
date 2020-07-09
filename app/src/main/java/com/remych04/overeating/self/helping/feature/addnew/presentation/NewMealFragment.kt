package com.remych04.overeating.self.helping.feature.addnew.presentation

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.datepicker.MaterialDatePicker
import com.remych04.overeating.self.helping.R
import com.remych04.overeating.self.helping.base.ext.binding
import com.remych04.overeating.self.helping.base.ext.setToolbarBackNavigation
import com.remych04.overeating.self.helping.data.MealDto
import com.remych04.overeating.self.helping.databinding.NewMealFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NewMealFragment : Fragment(R.layout.new_meal_fragment) {

    private val bind by binding { NewMealFragmentBinding.bind(this.requireView()) }
    private val viewModel by viewModel<NewMealViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbarBackNavigation(resources.getString(R.string.new_meal_fragment_title), viewModel)
        viewModel.getData().observe(viewLifecycleOwner, Observer { locationList ->
            bind.locationEditText.setAdapter(
                ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_list_item_1,
                    locationList
                )
            )
        })
        bind.addNewMealButton.setOnClickListener {
            //TODO делать проверки заполнености текстовых полей
            viewModel.addNewMeal(
                MealDto(
                    meal = bind.mealEditText.text.toString(),
                    feelings = bind.feelingsEditText.text.toString(),
                    location = bind.locationEditText.text.toString(),
                    date = dateFormat.parse(bind.dateTextView.text.toString()).time,
                    unnecessary = bind.necessityCheckbox.isChecked,
                    replacement = bind.replacementCheckbox.isChecked
                )
            )
        }
        bind.datePickerButton.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker().build()
            datePicker.addOnPositiveButtonClickListener { date ->
                bind.dateTextView.text = dateFormat.format(Date(date))
            }
            datePicker.show(parentFragmentManager, datePicker.toString())
        }
    }

    companion object {
        //TODO вынести формат в утилс и для первого раза оставить русскую локаль
        private val dateFormat = SimpleDateFormat("dd.MMMM.yyyy", Locale.getDefault())
        fun getInstance() = NewMealFragment()
    }
}
