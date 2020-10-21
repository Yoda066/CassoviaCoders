package com.example.cassoviacoders.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

    //na aktualne pocasie
    @GET("weather")
    fun getCurrentWeatherByCityName(
        @Query("q") name: String
    ): Call<CurrentWeatherRetrofit>


    //na denne predpovede
    @GET("onecall")
    fun getDailyForecastByCityCoord(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("exclude") exclude: String = "minutely, hourly, alerts"
    ): Call<DailyForecastResponse>

}