package com.example.cassoviacoders.db

import androidx.room.Embedded

//lokacia s jej poslednou znamou teplotou
data class LocationPojo(

    @Embedded
    val location: Location,

    //posledna znama teplota
    val lastTemp: Double

)