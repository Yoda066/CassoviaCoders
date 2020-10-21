package com.example.cassoviacoders.db

import androidx.room.Entity
import androidx.room.ForeignKey
import com.example.cassoviacoders.retrofit.Weather
import java.util.*

@Entity(
    tableName = "current_weather",
    foreignKeys = [
        ForeignKey(
            entity = Location::class,
            parentColumns = arrayOf("locId"),
            childColumns = arrayOf("locId")
        )],
    primaryKeys = ["locId", "dayId"]
)
data class CurrentWeather(
    //id lokacie predpovede
    var locId: Long,

    //poradove cislo dna
    val dayId: Int,

    val dateTime: Date,

    val weather: Weather?,

    val temp_mean: Double?,
    val temp_min: Double?,
    val temp_max: Double?,

    val sunrise: Date,
    val sunset: Date,

    val humidity: Double?,
    val pressure: Double?,
    val wind_speed: Double?
)