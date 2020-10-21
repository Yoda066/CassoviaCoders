package com.example.cassoviacoders.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
abstract class CurrentWeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(items: List<CurrentWeather>)

    @Query("SELECT * FROM current_weather WHERE locId = :locId AND dayId = :dayId")
    abstract fun getCurrentWeatherForLocationLiveData(
        locId: Long,
        dayId: Int
    ): LiveData<CurrentWeather>

    @Query("SELECT * FROM current_weather WHERE locId = :locId AND dayId = :dayId")
    abstract fun getCurrentWeatherForLocation(locId: Long, dayId: Int): CurrentWeather

    @Query("SELECT * FROM current_weather")
    abstract fun getAll(): List<CurrentWeather>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(currentWeather: CurrentWeather)
}
