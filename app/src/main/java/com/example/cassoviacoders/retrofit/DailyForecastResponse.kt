package com.example.cassoviacoders.retrofit

import com.google.gson.annotations.SerializedName

//Trieda sluziaca na parsovanie predpovede na buduce dni.
class DailyForecastResponse(
    val cod: Int,

    @field:SerializedName("daily")
    val list: List<DailyWeatherRetrofit?>?
)