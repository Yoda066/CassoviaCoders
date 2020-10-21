package com.example.cassoviacoders.db

import androidx.room.TypeConverter
import com.example.cassoviacoders.retrofit.Temperature
import com.example.cassoviacoders.retrofit.Weather
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

class BasicConverter {

    @TypeConverter
    fun toWeather(text: String?): Weather? {
        text?.let {
            return Gson().fromJson(it, Weather::class.java)
        }
        return null
    }

    @TypeConverter
    fun weatherToString(weather: Weather?): String? {
        if (weather == null) {
            return null
        }

        return Gson().toJson(weather)
    }

    @TypeConverter
    fun toDate(date: Long?): Date? {
        date?.let {
            return Date(it)
        }
        return null
    }

    @TypeConverter
    fun dateToLong(date: Date?): Long? {
        date?.let {
            return it.time
        }

        return null
    }
}
