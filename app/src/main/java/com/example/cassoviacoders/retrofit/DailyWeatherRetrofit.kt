package com.example.cassoviacoders.retrofit

import com.google.gson.annotations.SerializedName
import java.util.*

//Trieda sluziaca na parsovanie daily predpovede pre jednotlivy den
data class DailyWeatherRetrofit(
    @field:SerializedName("dt")
    val dt: Long,

    @field:SerializedName("sunrise")
    val sunrise: Long? = null,

    @field:SerializedName("sunset")
    val sunset: Long? = null,

    @field:SerializedName("temp")
    val temperature: Temperature? = null,

    val humidity: Double?,
    val pressure: Double?,

    @field:SerializedName("weather")
    val weather: List<Weather?>?,

    val wind_speed: Double?
) {

    val dateTime: Date
        get() {
            return Date(dt * 1000L)
        }
}


data class Temperature(
    val day: Double?,
    val min: Double?,
    val max: Double?,
    val night: Double?,
    val eve: Double?,
    val morn: Double?
)

data class Weather(
    val id: Int,
    val main: String,
    val description: String
)