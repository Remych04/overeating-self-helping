package com.remych04.overeating.self.helping.feature.daylist.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.remych04.overeating.self.helping.data.MealDto
import com.remych04.overeating.self.helping.databinding.MealItemBinding

class MealViewHolder(private val view: MealItemBinding) : RecyclerView.ViewHolder(view.root) {

    fun bind(item: MealDto) {
        view.mealItem.text = item.meal
        view.feelItem.text = item.feelings
        view.isUnnecessaryItem.text = if (item.unnecessary) {
            "лишнее"
        } else {
            "Не лишнее"
        }
        view.isReplacementItem.text = if (item.replacement) {
            "Заменяемое"
        } else {
            "Не заменяемое"
        }
    }
}