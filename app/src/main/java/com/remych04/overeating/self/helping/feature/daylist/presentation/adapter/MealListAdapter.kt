package com.remych04.overeating.self.helping.feature.daylist.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.remych04.overeating.self.helping.databinding.MealItemBinding
import com.remych04.overeating.self.helping.feature.daylist.data.MealDto

class MealListAdapter() : RecyclerView.Adapter<MealViewHolder>() {

    private lateinit var mealList: List<MealDto>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MealItemBinding.inflate(inflater, parent, false)
        return MealViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        holder.bind(mealList[position])
    }

    override fun getItemCount() = mealList.size

    fun setList(list: List<MealDto>) {
        mealList = list
        notifyDataSetChanged()
    }
}