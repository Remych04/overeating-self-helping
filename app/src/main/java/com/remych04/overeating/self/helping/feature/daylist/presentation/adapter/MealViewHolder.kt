package com.remych04.overeating.self.helping.feature.daylist.presentation.adapter

import android.widget.ArrayAdapter
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.ListPopupWindow
import androidx.recyclerview.widget.RecyclerView
import com.remych04.overeating.self.helping.R
import com.remych04.overeating.self.helping.base.ext.epochToFormattedDate
import com.remych04.overeating.self.helping.data.MealDto
import com.remych04.overeating.self.helping.databinding.MealItemBinding

class MealViewHolder(private val view: MealItemBinding) : RecyclerView.ViewHolder(view.root) {

    fun bind(
        item: MealDto,
        clickListener: ((MealDto, SpinnerEvent) -> Unit)?
    ) {
        view.dateItem.text = item.date.epochToFormattedDate()
        view.mealItem.text = item.meal
        view.placeItem.text = item.location
        view.feelItem.text = item.feelings
        view.necessityCheckboxItem.isChecked = item.unnecessary
        view.replacementCheckboxItem.isChecked = item.replacement
        initPopupMenu(view.dropdownMenuIcon, item, clickListener)
    }

    private fun initPopupMenu(
        dropdownView: AppCompatImageView,
        mealItem: MealDto,
        clickListener: ((MealDto, SpinnerEvent) -> Unit)?
    ) {
        val adapter = ArrayAdapter.createFromResource(
            dropdownView.context,
            R.array.item_action_array,
            R.layout.support_simple_spinner_dropdown_item
        )
        val listPopupWindow = ListPopupWindow(dropdownView.context).apply {
            anchorView = dropdownView
            width = 300
            isModal = true
            setAdapter(adapter)
        }
        listPopupWindow.setOnItemClickListener { _, _, position, _ ->
            listPopupWindow.dismiss()
            when (position) {
                0 -> clickListener?.invoke(mealItem, SpinnerEvent.CHANGE)
            }
        }
        dropdownView.setOnClickListener {
            listPopupWindow.show()
        }
    }
}