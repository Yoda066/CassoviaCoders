package com.example.cassoviacoders.utils

import com.example.cassoviacoders.db.MyCurrentWeather
import net.aksingh.owmjapis.model.CurrentWeather
import net.aksingh.owmjapis.model.param.System
import net.aksingh.owmjapis.model.param.Weather
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

//    @JvmStatic
//    fun getDayTime(systemData: System?): String {
//        val start = systemData?.sunriseDateTime?.time ?: return ""
//        val end = systemData.sunsetDateTime?.time ?: return ""
//
//        val millis = end - start
//        return String.format(
//            "%dh %dm",
//            TimeUnit.MILLISECONDS.toHours(millis),
//            TimeUnit.MILLISECONDS.toMinutes(millis) % 60
//        )
//    }

    @JvmStatic
    fun getDayTime(systemData: MyCurrentWeather?): String {
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
            return it.description
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