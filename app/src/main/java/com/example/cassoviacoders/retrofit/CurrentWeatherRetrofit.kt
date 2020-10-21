package com.example.cassoviacoders.retrofit

import com.google.gson.annotations.SerializedName
import java.util.*

data class CurrentWeatherRetrofit(
    @field:SerializedName("dt")
    val dt: Long,

    @field:SerializedName("main")
    val main: Main,

    @field:SerializedName("sys")
    val system: System2,


    @field:SerializedName("weather")
    val weather: List<Weather?>,

    @field:SerializedName("wind")
    val wind: Wind
) {

    val dateTime: Date
        get() {
            return Date(dt * 1000L)
        }
}

data class Main(
    val temp: Double,
    val temp_min: Double,
    val temp_max: Double,
    val pressure: Double,
    val humidity: Double
)

data class System2(
    val id: Int,
    val sunrise: Long,
    val sunset: Long

)

data class Wind(
    val speed: Double
)
