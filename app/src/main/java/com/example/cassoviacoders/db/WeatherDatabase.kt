package com.example.cassoviacoders.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Database(
    entities = [
        Location::class, DailyWeather::class, CurrentWeather::class
    ],
    exportSchema = false,
    version = 1
)
@TypeConverters(BasicConverter::class)
abstract class WeatherDatabase : RoomDatabase() {

    companion object {
        lateinit var context: Context
        var useInMemoryDb = false
        val DATABASE_NAME = "weather_db"

        @Volatile
        private var INSTANCE: WeatherDatabase? = null

        @JvmStatic
        fun getInstance(): WeatherDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: createDatabase(context).also { INSTANCE = it }
            }


        private fun createDatabase(context: Context): WeatherDatabase {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                WeatherDatabase::class.java,
                DATABASE_NAME
            )
                .allowMainThreadQueries()
                .setQueryExecutor { CoroutineScope(Dispatchers.IO).launch { it.run() } }
                .setJournalMode(JournalMode.WRITE_AHEAD_LOGGING)
                .build()

            INSTANCE!!.addDefaults()
            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }

        fun useInMemoryDb() {
            useInMemoryDb = true
        }
    }

    private fun addDefaults() {
        val locationDao = locationDao()
        if (locationDao.getAll().isEmpty()) {
            locationDao().insertAll(
                listOf(
                    Location(1, "Bratislava", "48.148598", "17.107748", 3060972),
                    Location(2, "Humenné", "48.938259", "21.907690", 724627),
                    Location(3, "Koromľa", "48.714940", "22.292480", 690548),
                    Location(4, "Košice", "48.716385", "21.261074", 865084),
                    Location(5, "Michalovce", "48.7514383", "21.9211949", 724144),
                    Location(6, "Sobrance", "48.7446501", "22.1806628", 723554)
                )
            )
        }
    }


    abstract fun currentWeatherDao(): CurrentWeatherDao
    abstract fun dailyWeatherDao(): DailyWeatherDao
    abstract fun locationDao(): LocationDao
}