package com.remych04.overeating.self.helping

import androidx.fragment.app.Fragment
import com.remych04.overeating.self.helping.feature.calendar.CalendarFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

sealed class Screens {
    class CalendarFragmentScreen : SupportAppScreen() {
        override fun getFragment(): Fragment? {
            return CalendarFragment.getInstance()
        }
    }
}