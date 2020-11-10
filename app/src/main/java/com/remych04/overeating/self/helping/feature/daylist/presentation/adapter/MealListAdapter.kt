package com.remych04.overeating.self.helping.feature.daylist.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.remych04.overeating.self.helping.R
import com.remych04.overeating.self.helping.data.MealDto

class MealListAdapter(
    private var clickListener: ((MealDto, SpinnerEvent) -> Unit)? = null
) : RecyclerView.Adapter<MealViewHolder>() {

    private var mealList: List<MealDto>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.meal_item, parent, false)
        return MealViewHolder(view)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        mealList?.let { list ->
            holder.bind(list[position], clickListener)
        }
    }

    override fun getItemCount() = mealList?.size ?: 0

    fun setList(list: List<MealDto>) {
        mealList = list
        notifyDataSetChanged()
    }
}