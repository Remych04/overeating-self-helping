package com.remych04.overeating.self.helping.feature.addnew.presentation

import android.os.Parcel
import android.os.Parcelable
import com.google.android.material.datepicker.CalendarConstraints

class MaxDateCalendarValidator(private val maxDate: Long) : CalendarConstraints.DateValidator {

    constructor(parcel: Parcel) : this(parcel.readLong())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(maxDate)
    }

    override fun isValid(date: Long): Boolean {
        return date <= maxDate
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<MaxDateCalendarValidator> {
        override fun createFromParcel(parcel: Parcel): MaxDateCalendarValidator {
            return MaxDateCalendarValidator(parcel)
        }

        override fun newArray(size: Int): Array<MaxDateCalendarValidator?> {
            return arrayOfNulls(size)
        }
    }
}