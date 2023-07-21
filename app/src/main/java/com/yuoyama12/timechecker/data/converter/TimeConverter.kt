package com.yuoyama12.timechecker.data.converter

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.yuoyama12.timechecker.data.Time

class TimeConverter {
    private val adapter =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
            .adapter(Time::class.java)

    @TypeConverter
    fun convertToTime(json: String): Time? {
        return adapter.fromJson(json)
    }

    @TypeConverter
    fun convertFromTime(time: Time): String {
        return adapter.toJson(time) ?: "[]"
    }
}