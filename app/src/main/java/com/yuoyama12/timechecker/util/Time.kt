package com.yuoyama12.timechecker.util

fun getHourList(): List<String> {
    val hours = mutableListOf<String>()
    for (i in 0..23) { hours.add(i.toTwoDigitString()) }
    return hours
}

fun getMinuteList(): List<String> {
    val minutes = mutableListOf<String>()
    for (i in 0..59) { minutes.add(i.toTwoDigitString()) }
    return minutes
}

fun Int.toTwoDigitString() =
    if (this.toString().length == 1) "0$this"
    else this.toString()

