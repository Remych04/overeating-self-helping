package com.remych04.overeating.self.helping.feature.daylist.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.remych04.overeating.self.helping.databinding.DaymeallistFragmentBinding
import com.remych04.overeating.self.helping.feature.daylist.data.MealDto
import com.remych04.overeating.self.helping.feature.daylist.presentation.adapter.MealListAdapter

class DayListFragment : Fragment() {

    private var _binding: DaymeallistFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DaymeallistFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).title = "Здарова"
        val adapter = MealListAdapter()
        binding.mealList.layoutManager = LinearLayoutManager(context)
        binding.mealList.adapter = adapter
        adapter.setList(listOf(MealDto("ПОНЧИКИ"), MealDto("ВАРЕНИКИ")))
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        fun getInstance() = DayListFragment()
    }
}