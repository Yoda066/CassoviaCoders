package com.example.cassoviacoders.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
abstract class CurrentWeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(items: List<MyCurrentWeather>)

    @Query("SELECT * FROM current_weather WHERE locId = :locId AND dayId = :dayId")
    abstract fun getCurrentWeatherForLocationLiveData(locId: Long, dayId: Int) : LiveData<MyCurrentWeather>

    @Query("SELECT * FROM current_weather WHERE locId = :locId AND dayId = :dayId")
    abstract fun getCurrentWeatherForLocation(locId: Long, dayId: Int) : MyCurrentWeather

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(currentWeather: MyCurrentWeather)

    @Query("SELECT * FROM current_weather")
    abstract fun getAll() : List<MyCurrentWeather>
}
