package com.example.cassoviacoders.retrofit

import android.os.NetworkOnMainThreadException
import com.example.cassoviacoders.db.Location
import com.example.cassoviacoders.db.MyCurrentWeather
import com.example.cassoviacoders.db.MyDailyWeather
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

class APIController {

    fun dailyWeatherForLocation(location: Location): List<MyDailyWeather>? {
        val api = weatherApi

        val apiCall = api.getDailyForecastByCityCoord(location.lat, location.lon)

        val apiResp: Response<DailyForecastResponse>
        try {
             apiResp = apiCall.execute()
        } catch (e: Exception) {
            return null
        }

        val weather: DailyForecastResponse? = apiResp.body()

        //vratim mapu mojuch db objektov
        return weather?.list!!.mapNotNull { t ->
            t?.let {
                MyDailyWeather(
                    location.locId,
                    TimeUnit.MILLISECONDS.toDays(it.dateTime.time).toInt(),
                    t.dateTime,
                    t.weather?.first(),
                    t.temperature?.day,
                    t.temperature?.min,
                    t.temperature?.max
                )
            }
        }.toList()
    }

    fun currentWeatherForLocation(location: Location): MyCurrentWeather? {
        val api = weatherApi

        val apiCall = api.getCurrentWeatherByCityName(location.title)

        val apiResp: Response<CurrentWeatherRetrofit>
        try {
             apiResp = apiCall.execute()
        } catch (e: Exception) {
            return null
        }

        if (apiResp.code() != 200) {
            return null
        }

        val weather: CurrentWeatherRetrofit? = apiResp.body()

        //vratm moj db objekt
        return MyCurrentWeather(
            location.locId,
            TimeUnit.MILLISECONDS.toDays(weather?.dateTime?.time ?: 0).toInt(),
            weather?.dateTime!!,
            weather.weather.first(),
            weather.main.temp,
            weather.main.temp_min,
            weather.main.temp_max,
            Date(weather.system.sunrise * 1000),
            Date(weather.system.sunset * 1000),
            weather.main.humidity,
            weather.main.pressure,
            weather.wind.speed
        )
    }


    companion object {
        private const val baseUrl: String = "https://api.openweathermap.org/data/2.5/"
        const val APIKEY = "338688277e40c7e89e7665301874aedf"

        private val weatherApi = createWeatherAPI()

        private fun createWeatherAPI(): WeatherAPI {
            val clientBuilder = OkHttpClient.Builder()

            clientBuilder.addInterceptor { chain ->
                val original = chain.request()
                val originalHttpUrl = original.url()

                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("appid", APIKEY)
                    .addQueryParameter("lang", "en")
                    .addQueryParameter("units", "metric")
                    .build()

                val requestBuilder = original.newBuilder()
                    .url(url)

                val request = requestBuilder.build()

                chain.proceed(request)
            }

            val client = clientBuilder.build()
            val gson = GsonBuilder().create()

            val builder = Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))

            return builder.build().create(WeatherAPI::class.java)
        }
    }
}