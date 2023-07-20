package com.yuoyama12.timechecker.ui.main

import androidx.lifecycle.ViewModel
import com.yuoyama12.timechecker.data.Time
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
) : ViewModel() {
    private val _checkResult: MutableStateFlow<Boolean?> = MutableStateFlow(null)
    val checkResult: StateFlow<Boolean?> = _checkResult

    fun checkIfTimeIsInRange(
        _time: Time,
        _start: Time,
        _end: Time
    ) {
        val time = convertStringIntoTime(_time.toTimeFormat())
        val start = convertStringIntoTime(_start.toTimeFormat())
        val end = convertStringIntoTime(_end.toTimeFormat())

        if (start < end) {
            _checkResult.value = start <= time && time < end
        } else if (start > end) {
            _checkResult.value = start <= time || time < end
        } else if (start == end) {
            _checkResult.value = start == time && time == end
        }
    }

    private fun convertStringIntoTime(string: String): LocalTime =
        LocalTime.parse(string)


}