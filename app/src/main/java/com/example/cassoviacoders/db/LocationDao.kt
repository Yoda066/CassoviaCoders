package com.example.cassoviacoders.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
abstract class LocationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(items: List<Location>)

    @Query(
        """SELECT l.*, IFNULL(d.temp_mean, '') AS lastTemp FROM location AS l 
        LEFT JOIN current_weather d ON (l.locId = d.locId AND d.dayId = :day)"""
    )
    abstract fun getLocationsPojo(day: Int): List<LocationPojo>

    @Query(
        """SELECT l.*, IFNULL(d.temp_mean, '') AS lastTemp FROM location AS l 
        LEFT JOIN current_weather d ON (l.locId = d.locId AND d.dayId = :day)"""
    )
    abstract fun getLocationsPojoLivedata(day: Int): LiveData<List<LocationPojo>>

    @Query("SELECT * FROM location")
    abstract fun getAll(): List<Location>


    @Query("SELECT IFNULL(GROUP_CONCAT(cityId), '') FROM location")
    abstract fun getAllCityIds(): String


}
