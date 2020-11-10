package com.remych04.overeating.self.helping.feature.addnew.presentation

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.button.MaterialButton
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.textview.MaterialTextView
import com.remych04.overeating.self.helping.R
import com.remych04.overeating.self.helping.base.ext.epochToFormattedDate
import com.remych04.overeating.self.helping.base.ext.formattedDateToEpochMilli
import com.remych04.overeating.self.helping.base.ext.getCurrentTime
import com.remych04.overeating.self.helping.base.ext.setToolbarBackNavigation
import com.remych04.overeating.self.helping.data.MealDto
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.LocalDate
import java.time.ZoneId

class NewMealFragment : Fragment(R.layout.new_meal_fragment) {

    private val viewModel by viewModel<NewMealViewModel>()
    private var mealInputLayout: TextInputLayout? = null
    private var mealEditText: TextInputEditText? = null
    private var locationEditText: AutoCompleteTextView? = null
    private var feelingsEditText: TextInputEditText? = null
    private var dateTextView: MaterialTextView? = null
    private var necessityCheckbox: MaterialCheckBox? = null
    private var replacementCheckbox: MaterialCheckBox? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mealInputLayout = view.findViewById<TextInputLayout>(R.id.meal_input_layout)
        mealEditText = view.findViewById<TextInputEditText>(R.id.meal_edit_text)
//        val locationInputLayout = view.findViewById<TextInputLayout>(R.id.location_input_layout)
        locationEditText = view.findViewById<AutoCompleteTextView>(R.id.location_edit_text)
//        val feelingsInputLayout = view.findViewById<TextInputLayout>(R.id.feelings_input_layout)
        feelingsEditText = view.findViewById<TextInputEditText>(R.id.feelings_edit_text)
        val datePickerButton = view.findViewById<MaterialButton>(R.id.date_picker_button)
        dateTextView = view.findViewById<MaterialTextView>(R.id.date_text_view)
        necessityCheckbox = view.findViewById<MaterialCheckBox>(R.id.necessity_checkbox)
        replacementCheckbox = view.findViewById<MaterialCheckBox>(R.id.replacement_checkbox)
        val addNewMealButton = view.findViewById<MaterialButton>(R.id.add_new_meal_button)
        setToolbarBackNavigation(resources.getString(R.string.new_meal_fragment_title), viewModel)
        viewModel.getLocationsData()
            .observe(
                viewLifecycleOwner,
                Observer { locationList -> populateLocations(locationList, locationEditText!!) }
            )
        viewModel.getSuccessInsertData()
            .observe(
                viewLifecycleOwner,
                Observer { isSuccess -> showAddNewMealResultSnackbar(isSuccess, addNewMealButton) }
            )
        dateTextView!!.text = getCurrentTime()
        addNewMealButton.setOnClickListener {
            if (arguments != null) {
                changeMeal()
            } else {
                addMeal()
            }
        }
        datePickerButton.setOnClickListener { showCalendar(dateTextView!!) }

        if (arguments != null) {
            val item = arguments?.getSerializable(MEAL_ITEM_KEY) as MealDto
            mealEditText?.setText(item.meal, TextView.BufferType.EDITABLE)
            feelingsEditText?.setText(item.feelings, TextView.BufferType.EDITABLE)
            locationEditText?.setText(item.location, TextView.BufferType.EDITABLE)
            dateTextView?.text = item.date.epochToFormattedDate()
            necessityCheckbox?.isChecked = item.unnecessary
            replacementCheckbox?.isChecked = item.replacement
        }
    }

    private fun showAddNewMealResultSnackbar(isSuccess: Boolean, addNewMealButton: MaterialButton) {
        if (isSuccess) {
            Snackbar.make(
                addNewMealButton,
                getString(R.string.add_meal_success_snack),
                Snackbar.LENGTH_SHORT
            ).show()
        } else {
            Snackbar.make(
                addNewMealButton,
                getString(R.string.add_meal_error_snack),
                Snackbar.LENGTH_SHORT
            )
                .setAction(getString(R.string.add_meal_repeat_snack)) { addMeal() }
                .show()
        }

    }

    private fun populateLocations(locationList: List<String>, locationEditText: AutoCompleteTextView) {
        locationEditText.setAdapter(
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

    private fun showCalendar(dateTextView: MaterialTextView) {
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
            dateTextView.text = date.epochToFormattedDate()
        }

        datePicker.show(parentFragmentManager, datePicker.toString())
    }

    private fun createMealDto(id: Long? = null): MealDto {
        //TODO делать проверки заполнености текстовых полей
        return MealDto(
            id = id,
            meal = mealEditText?.text.toString(),
            feelings = feelingsEditText?.text.toString(),
            location = locationEditText?.text.toString(),
            date = dateTextView!!.text.formattedDateToEpochMilli(),
            unnecessary = necessityCheckbox!!.isChecked,
            replacement = replacementCheckbox!!.isChecked
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
