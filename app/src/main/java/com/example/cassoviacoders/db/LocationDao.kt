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

    @Query("SELECT * FROM location")
    abstract fun getLocationsLiveData() : LiveData<List<Location>>

    @Query("SELECT * FROM location")
    abstract fun getAll() : List<Location>


    @Query("SELECT IFNULL(GROUP_CONCAT(cityId), '') FROM location")
    abstract fun getAllCityIds() : String


}
