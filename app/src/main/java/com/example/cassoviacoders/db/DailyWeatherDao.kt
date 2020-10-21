package com.example.cassoviacoders.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
abstract class DailyWeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(items: List<DailyWeather>)

    @Query("SELECT * FROM daily_weather")
    abstract fun getAll(): List<DailyWeather>

    @Query("SELECT * FROM daily_weather WHERE locId = :locId AND dayId = :dayId")
    abstract fun getDailyWeatherForLocation(locId: Long, dayId: Int): LiveData<DailyWeather>

}
