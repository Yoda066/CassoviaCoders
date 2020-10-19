package com.example.cassoviacoders

class LocationsRepo {

    fun getDefaultLocations() : List<Location> = listOf(
        Location( 1, "Bratislava"),
        Location( 2, "Humenné"),
        Location( 3, "Koromľa"),
        Location( 4, "Košice"),
        Location( 5, "Michalovce"),
        Location( 6, "sobrance")
    )
}