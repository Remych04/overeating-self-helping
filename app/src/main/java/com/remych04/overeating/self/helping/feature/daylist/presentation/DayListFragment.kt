package com.remych04.overeating.self.helping.feature.daylist.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.remych04.overeating.self.helping.R
import com.remych04.overeating.self.helping.base.BaseFragment
import com.remych04.overeating.self.helping.databinding.DaymeallistFragmentBinding
import com.remych04.overeating.self.helping.feature.daylist.presentation.adapter.MealListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class DayListFragment : BaseFragment() {

    private var _binding: DaymeallistFragmentBinding? = null
    private val binding get() = _binding!!
    private val model by viewModel<DayListViewModel>()
    private lateinit var adapter: MealListAdapter

    override fun getViewModel() = model

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
        setTitle(resources.getString(R.string.day_list_fragment_title), false)
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