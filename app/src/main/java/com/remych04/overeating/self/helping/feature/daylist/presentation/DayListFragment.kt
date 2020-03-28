package com.remych04.overeating.self.helping.feature.daylist.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.remych04.overeating.self.helping.databinding.DaymeallistFragmentBinding
import com.remych04.overeating.self.helping.feature.daylist.presentation.adapter.MealListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class DayListFragment : Fragment() {

    private var _binding: DaymeallistFragmentBinding? = null
    private val binding get() = _binding!!
    private val model by viewModel<DayListViewModel>()
    private lateinit var adapter: MealListAdapter

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
        initAdapter()
        binding.refreshMealList.setOnRefreshListener {
            model.loadMealList()
        }
        binding.addMealButton.setOnClickListener {
            model.addMealClick()
        }
        model.getData().observe(viewLifecycleOwner, Observer {
            binding.refreshMealList.isRefreshing = false
            adapter.setList(it)
        })
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun initAdapter() {
        adapter = MealListAdapter()
        binding.mealList.layoutManager = LinearLayoutManager(context)
        binding.mealList.adapter = adapter
    }

    companion object {
        fun getInstance() = DayListFragment()
    }
}