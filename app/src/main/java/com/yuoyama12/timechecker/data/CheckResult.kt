package com.yuoyama12.timechecker.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CheckResult(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val selectedTime: Time,
    val startTime: Time,
    val endTime: Time,
    val isIncluded: Boolean
)
