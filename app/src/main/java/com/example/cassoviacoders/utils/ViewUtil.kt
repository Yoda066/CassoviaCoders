package com.example.cassoviacoders.utils

import com.example.cassoviacoders.db.CurrentWeather
import java.util.*
import java.util.concurrent.TimeUnit

object ViewUtil {
    @JvmStatic
    fun getNiceTime(time: Date?): String {
        return FormatHelper.formatTime(time)
    }

    @JvmStatic
    fun getMainDateTime(time: Date?): String {
        return FormatHelper.formatMainDate(time)
    }

    @JvmStatic
    fun dailyDate(date: Date?): String {
        return FormatHelper.formatDailyDate(date)
    }

    @JvmStatic
    fun getDayTime(systemData: CurrentWeather?): String {
        val start = systemData?.sunrise?.time ?: return ""
        val end = systemData.sunset.time

        val millis = end - start
        return String.format(
            "%dh %dm",
            TimeUnit.MILLISECONDS.toHours(millis),
            TimeUnit.MILLISECONDS.toMinutes(millis) % 60
        )
    }

    //meni m/s na km/h
    @JvmStatic
    fun convertToKilometresPerHour(speed: Double?): String {
        speed?.let {
            return String.format("%.0f", (speed * 3.6))
        }
        return "0"
    }

    //meni m/s na km/h
    @JvmStatic
    fun getWeatherDescription(weather: com.example.cassoviacoders.retrofit.Weather?): String {
        weather?.let {
            return it.main
        }
        return ""
    }

    //meni m/s na km/h
    @JvmStatic
    fun formatPressure(pressure: Double?): String {
        pressure?.let {
            if (it == 0.0) {
                return ""
            }
            return (it / 1000.0).toString()
        }
        return ""
    }
}