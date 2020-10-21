package com.example.cassoviacoders.db

import androidx.room.Entity
import androidx.room.ForeignKey
import com.example.cassoviacoders.retrofit.Weather
import java.util.*

@Entity(
    tableName = "daily_weather",
    foreignKeys = [
        ForeignKey(
            entity = Location::class,
            parentColumns = arrayOf("locId"),
            childColumns = arrayOf("locId")
        )],
    primaryKeys = ["locId", "dayId"]
)
data class DailyWeather(
    //id lokacie predpovede
    var locId: Long,

    //poradove cislo dna
    val dayId: Int,

    val dateTime: Date,

    val weather: Weather?,

    val temp_day: Double?,
    val temp_min: Double?,
    val temp_max: Double?
)