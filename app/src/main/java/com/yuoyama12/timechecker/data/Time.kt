package com.yuoyama12.timechecker.data

import android.os.Parcelable
import com.yuoyama12.timechecker.util.toTwoDigitString
import kotlinx.parcelize.Parcelize

@Parcelize
data class Time(
    val hour: String = 0.toTwoDigitString(),
    val minute: String = 0.toTwoDigitString(),
) : Parcelable {
    fun toTimeFormat(): String = "${this.hour}:${this.minute}"
}

