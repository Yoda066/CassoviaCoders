package com.example.cassoviacoders

import androidx.lifecycle.LiveData
import com.example.cassoviacoders.db.LocationPojo
import com.example.cassoviacoders.db.WeatherDatabase
import com.example.cassoviacoders.utils.FormatHelper

class LocationsRepo {

    fun getDefaultLocations(): List<LocationPojo> =
        WeatherDatabase.getInstance().locationDao().getLocationsPojo(FormatHelper.getDay())

    fun getDefaultLocationsLiveData(): LiveData<List<LocationPojo>> =
        WeatherDatabase.getInstance().locationDao().getLocationsPojoLivedata(FormatHelper.getDay())
}