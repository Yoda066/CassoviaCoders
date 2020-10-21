package com.example.cassoviacoders

import com.example.cassoviacoders.db.Location
import com.example.cassoviacoders.db.WeatherDatabase

class LocationsRepo {

    fun getDefaultLocations() : List<Location> = WeatherDatabase.getInstance().locationDao().getAll()
}