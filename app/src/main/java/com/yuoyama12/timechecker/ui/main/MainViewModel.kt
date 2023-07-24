package com.yuoyama12.timechecker.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuoyama12.timechecker.data.CheckResult
import com.yuoyama12.timechecker.data.CheckResultRepository
import com.yuoyama12.timechecker.data.Time
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: CheckResultRepository,
) : ViewModel() {
    private var previousCheckResult: CheckResult? = null

    fun checkIfTimeIsIncludedInRange(
        _time: Time,
        _start: Time,
        _end: Time
    ): Boolean? {
        val time = convertStringIntoTime(_time.toTimeFormat())
        val start = convertStringIntoTime(_start.toTimeFormat())
        val end = convertStringIntoTime(_end.toTimeFormat())

        return if (start < end) {
            start <= time && time < end
        } else if (start > end) {
            start <= time || time < end
        } else if (start == end) {
            start == time && time == end
        } else {
            null
        }
    }

    fun insertCheckResult(checkResult: CheckResult) {
        restoreCheckResult(checkResult)

        viewModelScope.launch(Dispatchers.IO) {
            repo.insert(checkResult)
        }
    }

    fun isSameToPreviousResult(
        time: Time,
        startTime: Time,
        endTime: Time
    ): Boolean {
        return if (previousCheckResult == null) { false }
        else {
            previousCheckResult!!.selectedTime == time
                    && previousCheckResult!!.startTime == startTime
                    && previousCheckResult!!.endTime == endTime
        }
    }

    private fun convertStringIntoTime(string: String): LocalTime =
        LocalTime.parse(string)

    private fun restoreCheckResult(checkResult: CheckResult) {
        previousCheckResult = checkResult
    }
}