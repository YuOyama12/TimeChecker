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

    fun insertCheckResult(
        time: Time,
        start: Time,
        end: Time,
        isIncluded: Boolean
    ) {
        val checkResult =
            CheckResult(
                selectedTime = time,
                startTime = start,
                endTime = end,
                isIncluded = isIncluded
            )

        viewModelScope.launch(Dispatchers.IO) {
            repo.insert(checkResult)
        }
    }

    private fun convertStringIntoTime(string: String): LocalTime =
        LocalTime.parse(string)


}