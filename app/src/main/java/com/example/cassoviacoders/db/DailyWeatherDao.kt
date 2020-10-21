package com.example.cassoviacoders.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
abstract class DailyWeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(items: List<MyDailyWeather>)

    @Query("SELECT * FROM daily_weather")
    abstract fun getLocationsLiveData() : LiveData<List<MyDailyWeather>>

    @Query("SELECT * FROM daily_weather")
    abstract fun getAll() : List<MyDailyWeather>
}
