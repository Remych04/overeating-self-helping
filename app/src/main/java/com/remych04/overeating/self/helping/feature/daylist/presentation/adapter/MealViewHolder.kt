package com.remych04.overeating.self.helping.feature.daylist.presentation.adapter

import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.ListPopupWindow
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.textview.MaterialTextView
import com.remych04.overeating.self.helping.R
import com.remych04.overeating.self.helping.base.ext.epochToFormattedDate
import com.remych04.overeating.self.helping.data.MealDto

class MealViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private val mealItem = view.findViewById<MaterialTextView>(R.id.meal_item)
    private val dropdownMenuIcon = view.findViewById<AppCompatImageView>(R.id.dropdown_menu_icon)
    private val placeItem = view.findViewById<MaterialTextView>(R.id.place_item)
    private val dateItem = view.findViewById<MaterialTextView>(R.id.date_item)
    private val feelItem = view.findViewById<MaterialTextView>(R.id.feel_item)
    private val necessityCheckboxItem = view.findViewById<MaterialCheckBox>(R.id.necessity_checkbox_item)
    private val replacementCheckboxItem = view.findViewById<MaterialCheckBox>(R.id.replacement_checkbox_item)

    fun bind(
        item: MealDto,
        clickListener: ((MealDto, SpinnerEvent) -> Unit)?
    ) {
        dateItem.text = item.date.epochToFormattedDate()
        mealItem.text = item.meal
        placeItem.text = item.location
        feelItem.text = item.feelings
        necessityCheckboxItem.isChecked = item.unnecessary
        replacementCheckboxItem.isChecked = item.replacement
        initPopupMenu(dropdownMenuIcon, item, clickListener)
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