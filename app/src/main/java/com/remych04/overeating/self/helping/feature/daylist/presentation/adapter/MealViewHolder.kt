package com.remych04.overeating.self.helping.feature.daylist.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.remych04.overeating.self.helping.databinding.MealItemBinding
import com.remych04.overeating.self.helping.data.MealDto

class MealViewHolder(private val view: MealItemBinding) : RecyclerView.ViewHolder(view.root) {

    fun bind(item: MealDto) {
        view.text.text = item.meal
    }
}