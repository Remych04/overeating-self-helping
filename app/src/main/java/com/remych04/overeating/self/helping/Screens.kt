package com.remych04.overeating.self.helping

import androidx.fragment.app.Fragment
import com.remych04.overeating.self.helping.data.MealDto
import com.remych04.overeating.self.helping.feature.addnew.presentation.NewMealFragment
import com.remych04.overeating.self.helping.feature.calendar.CalendarFragment
import com.remych04.overeating.self.helping.feature.daylist.presentation.DayListFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

sealed class Screens {
    class CalendarFragmentScreen : SupportAppScreen() {
        override fun getFragment(): Fragment? {
            return CalendarFragment.getInstance()
        }
    }

    class DayListFragmentScreen : SupportAppScreen() {
        override fun getFragment(): Fragment? {
            return DayListFragment.getInstance()
        }
    }

    class NewMealFragmentScreen : SupportAppScreen() {
        override fun getFragment(): Fragment? {
            return NewMealFragment.getInstance()
        }
    }

    class NewMealFragmentScreenWithParams(private val mealItem: MealDto) : SupportAppScreen() {
        override fun getFragment(): Fragment? {
            return NewMealFragment.getInstance(mealItem)
        }
    }
}