package com.remych04.overeating.self.helping.feature.addnew.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.remych04.overeating.self.helping.R
import com.remych04.overeating.self.helping.base.BaseFragment
import com.remych04.overeating.self.helping.data.MealDto
import com.remych04.overeating.self.helping.databinding.NewMealFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class NewMealFragment : BaseFragment() {

    private var _binding: NewMealFragmentBinding? = null
    private val binding get() = _binding!!
    private val model by viewModel<NewMealViewModel>()

    override fun getViewModel() = model

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = NewMealFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(resources.getString(R.string.new_meal_fragment_title))
        binding.addNewMealButton.setOnClickListener {
            model.addNewMeal(
                MealDto(
                    meal = binding.mealEditText.text.toString(),
                    feelings = binding.feelingsEditText.text.toString(),
                    date = Calendar.getInstance().timeInMillis,
                    unnecessary = binding.isUnnecessary.isChecked,
                    replacement = binding.isReplacement.isChecked
                )
            )
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        fun getInstance() = NewMealFragment()
    }
}
