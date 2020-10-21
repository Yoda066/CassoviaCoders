package com.example.cassoviacoders.retrofit

import com.google.gson.annotations.SerializedName

class DailyForecastResponse(
    val cod: Int,

    @field:SerializedName("daily")
    val list: List<DailyWeatherRetrofit?>?
)