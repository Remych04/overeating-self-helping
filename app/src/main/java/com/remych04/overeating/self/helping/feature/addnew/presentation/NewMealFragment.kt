package com.remych04.overeating.self.helping.feature.addnew.presentation

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import com.remych04.overeating.self.helping.R
import com.remych04.overeating.self.helping.base.ext.binding
import com.remych04.overeating.self.helping.base.ext.epochToFormattedDate
import com.remych04.overeating.self.helping.base.ext.formattedDateToEpochMilli
import com.remych04.overeating.self.helping.base.ext.getCurrentTime
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
        viewModel.getLocationsData()
            .observe(
                viewLifecycleOwner,
                Observer { locationList -> populateLocations(locationList) }
            )
        viewModel.getSuccessInsertData()
            .observe(
                viewLifecycleOwner,
                Observer { isSuccess -> showAddNewMealResultSnackbar(isSuccess) }
            )
        bind.dateTextView.text = getCurrentTime()
        bind.addNewMealButton.setOnClickListener {
            if (arguments != null) {
                changeMeal()
            } else {
                addMeal()
            }
        }
        bind.datePickerButton.setOnClickListener { showCalendar() }

        if (arguments != null) {
            val item = arguments?.getSerializable(MEAL_ITEM_KEY) as MealDto
            bind.mealEditText.setText(item.meal, TextView.BufferType.EDITABLE)
            bind.feelingsEditText.setText(item.feelings, TextView.BufferType.EDITABLE)
            bind.locationEditText.setText(item.location, TextView.BufferType.EDITABLE)
            bind.dateTextView.text = item.date.epochToFormattedDate()
            bind.necessityCheckbox.isChecked = item.unnecessary
            bind.replacementCheckbox.isChecked = item.replacement
        }
    }

    private fun showAddNewMealResultSnackbar(isSuccess: Boolean) {
        if (isSuccess) {
            Snackbar.make(
                bind.addNewMealButton,
                getString(R.string.add_meal_success_snack),
                Snackbar.LENGTH_SHORT
            ).show()
        } else {
            Snackbar.make(
                bind.addNewMealButton,
                getString(R.string.add_meal_error_snack),
                Snackbar.LENGTH_SHORT
            )
                .setAction(getString(R.string.add_meal_repeat_snack)) { addMeal() }
                .show()
        }

    }

    private fun populateLocations(locationList: List<String>) {
        bind.locationEditText.setAdapter(
            ArrayAdapter(
                requireContext(),
                android.R.layout.simple_list_item_1,
                locationList
            )
        )
    }

    private fun changeMeal() {
        val item = arguments?.getSerializable(MEAL_ITEM_KEY) as MealDto
        viewModel.changeMeal(createMealDto(item.id))
    }

    private fun addMeal() {
        viewModel.addNewMeal(createMealDto())
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

    private fun createMealDto(id: Long? = null): MealDto {
        //TODO делать проверки заполнености текстовых полей
        return MealDto(
            id = id,
            meal = bind.mealEditText.text.toString(),
            feelings = bind.feelingsEditText.text.toString(),
            location = bind.locationEditText.text.toString(),
            date = bind.dateTextView.text.formattedDateToEpochMilli(),
            unnecessary = bind.necessityCheckbox.isChecked,
            replacement = bind.replacementCheckbox.isChecked
        )
    }

    companion object {
        private const val MEAL_ITEM_KEY = "meal_item_key"

        fun getInstance() = NewMealFragment()

        fun getInstance(mealItem: MealDto): NewMealFragment {
            val bundle = Bundle().apply { putSerializable(MEAL_ITEM_KEY, mealItem) }
            return NewMealFragment().apply {
                arguments = bundle
            }
        }
    }
}
