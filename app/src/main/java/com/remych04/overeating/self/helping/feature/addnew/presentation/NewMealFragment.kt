package com.remych04.overeating.self.helping.feature.addnew.presentation

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import com.remych04.overeating.self.helping.R
import com.remych04.overeating.self.helping.base.ext.binding
import com.remych04.overeating.self.helping.base.ext.epochToFormattedDate
import com.remych04.overeating.self.helping.base.ext.formattedDateToEpochMilli
import com.remych04.overeating.self.helping.base.ext.setToolbarBackNavigation
import com.remych04.overeating.self.helping.data.MealDto
import com.remych04.overeating.self.helping.databinding.NewMealFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.LocalDate
import java.time.ZoneId

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
        bind.addNewMealButton.setOnClickListener { addMeal() }
        bind.datePickerButton.setOnClickListener { showCalendar() }
    }

    private fun addMeal() {
        //TODO делать проверки заполнености текстовых полей

        viewModel.addNewMeal(
            MealDto(
                meal = bind.mealEditText.text.toString(),
                feelings = bind.feelingsEditText.text.toString(),
                location = bind.locationEditText.text.toString(),
                date = bind.dateTextView.text.formattedDateToEpochMilli(),
                unnecessary = bind.necessityCheckbox.isChecked,
                replacement = bind.replacementCheckbox.isChecked
            )
        )
    }

    private fun showCalendar() {
        val nextDay = LocalDate.now()
            .plusDays(1)
            .atStartOfDay(ZoneId.systemDefault())
            .toInstant()
        val constraintsBuilder = CalendarConstraints.Builder()
            .setValidator(MaxDateCalendarValidator(nextDay.toEpochMilli()))
            .build()
        val datePicker = MaterialDatePicker.Builder
            .datePicker()
            .setCalendarConstraints(constraintsBuilder)
            .build()
        datePicker.addOnPositiveButtonClickListener { date ->
            bind.dateTextView.text = date.epochToFormattedDate()
        }

        datePicker.show(parentFragmentManager, datePicker.toString())
    }

    companion object {
        fun getInstance() = NewMealFragment()
    }
}
