package com.remych04.overeating.self.helping.feature.daylist.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.remych04.overeating.self.helping.data.MealDto
import com.remych04.overeating.self.helping.databinding.MealItemBinding

class MealListAdapter() : RecyclerView.Adapter<MealViewHolder>() {

    private var mealList: List<MealDto>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MealItemBinding.inflate(inflater, parent, false)
        return MealViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        mealList?.let {
            holder.bind(it[position])
        }
    }

    override fun getItemCount() = mealList?.size ?: 0

    fun setList(list: List<MealDto>) {
        mealList = list
        notifyDataSetChanged()
    }
}