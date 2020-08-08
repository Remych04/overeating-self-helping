package com.remych04.overeating.self.helping.feature.daylist.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.remych04.overeating.self.helping.base.ext.epochToFormattedDate
import com.remych04.overeating.self.helping.data.MealDto
import com.remych04.overeating.self.helping.databinding.MealItemBinding

class MealViewHolder(private val view: MealItemBinding) : RecyclerView.ViewHolder(view.root) {

    fun bind(item: MealDto) {
        view.dateItem.text = item.date.epochToFormattedDate()
        view.mealItem.text = item.meal
        view.placeItem.text = item.location
        view.feelItem.text = item.feelings
        view.necessityCheckboxItem.isChecked = item.unnecessary
        view.replacementCheckboxItem.isChecked = item.replacement
    }
}