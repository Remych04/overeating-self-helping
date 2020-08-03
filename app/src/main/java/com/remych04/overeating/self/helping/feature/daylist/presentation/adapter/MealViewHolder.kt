package com.remych04.overeating.self.helping.feature.daylist.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.remych04.overeating.self.helping.base.ext.epochToFormattedDate
import com.remych04.overeating.self.helping.data.MealDto
import com.remych04.overeating.self.helping.databinding.MealItemBinding

class MealViewHolder(private val view: MealItemBinding) : RecyclerView.ViewHolder(view.root) {

    fun bind(item: MealDto) {
//        TransitionManager.beginDelayedTransition(view.root)
//        view.root.setOnClickListener {
//            if (view.unnecessaryItem.visibility == View.VISIBLE){
//                view.unnecessaryItem.visibility = View.GONE
//                view.replacementItem.visibility = View.GONE
//            }else{
//                view.unnecessaryItem.visibility = View.VISIBLE
//                view.replacementItem.visibility = View.VISIBLE
//            }
//        }

        view.mealItem.text = item.meal
        view.feelItem.text = item.feelings
        view.dateItem.text = item.date.epochToFormattedDate()
        //TODO сделать нормально
        view.unnecessaryItem.text = if (item.unnecessary) {
            "лишнее"
        } else {
            "Не лишнее"
        }
        view.replacementItem.text = if (item.replacement) {
            "Заменяемое"
        } else {
            "Не заменяемое"
        }
    }
}