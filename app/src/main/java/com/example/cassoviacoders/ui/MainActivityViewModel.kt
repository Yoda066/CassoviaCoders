package com.example.cassoviacoders.ui

import androidx.lifecycle.*
import com.example.cassoviacoders.db.Location
import com.example.cassoviacoders.LocationsRepo
import com.example.cassoviacoders.R
import com.example.cassoviacoders.db.MyCurrentWeather
import com.example.cassoviacoders.db.MyDailyWeather
import com.example.cassoviacoders.db.WeatherDatabase
import com.example.cassoviacoders.retrofit.APIController
import com.example.cassoviacoders.retrofit.Weather
import com.example.cassoviacoders.utils.formatForSearch
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.aksingh.owmjapis.core.OWM
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivityViewModel : ViewModel() {
    val owm = OWM("338688277e40c7e89e7665301874aedf").apply {
        unit = OWM.Unit.METRIC
    }

    //hodnota vyhladavacieho pola
    val filterString = MutableLiveData<String>()

    //zoznam odifltrovanych lokacii
    private val _filteredLocations = MediatorLiveData<List<Location>>()
    val filteredLocations: LiveData<List<Location>> = _filteredLocations

    //zoznam vsetkych lokacii v databaze
    val loadedLocations: List<Location> = LocationsRepo()
        .getDefaultLocations()

    //aktualne zobrazena lokacia
    private val _actualLocation = MutableLiveData<Location?>()
    val actualLocation: LiveData<Location?> = _actualLocation

    val currentWeather: LiveData<MyCurrentWeather>

    init {
        _filteredLocations.postValue(LocationsRepo().getDefaultLocations())

        _filteredLocations.addSource(filterString) {
            _filteredLocations.postValue(loadedLocations.filter { t: Location ->
                t.title.formatForSearch().contains(it.formatForSearch())
            })
        }

        //aktualne pocasie sa nacitava z db podla aktualnej lokacie
        currentWeather = Transformations.switchMap(actualLocation) {
            it?.let {
                val currentDay = TimeUnit.MILLISECONDS.toDays(System.currentTimeMillis()).toInt()
                //live data pre aktualnu predpovet pre zvolenu lokaciu a aktualny den
                WeatherDatabase.getInstance().currentWeatherDao()
                    .getCurrentWeatherForLocationLiveData(it.locId, currentDay)
            }
        }
    }

    //Nacita data z webu, pretransformuje na nase db objkekty a ulozi do db.
    private fun loadAndSaveDataForLocation(location: Location) {
        CoroutineScope(Dispatchers.IO).launch {
            val currentWeather = APIController().currentWeatherForLocation(location)
            currentWeather?.let {
                WeatherDatabase.getInstance().currentWeatherDao().insert(it)
            }

            //dostanem z api moje db objekty dennych predpovedi
            val weatherWithLocations = APIController().dailyWeatherForLocation(location)
            weatherWithLocations?.let {
                //vlozim predpovede pre den do databazy
                WeatherDatabase.getInstance().dailyWeatherDao().insertAll(it)
            }
        }
    }


    //    NAVIGACIA:
    val actualFragmentId = MutableLiveData<Int?>()

    fun switchToLocationDetail(location: Location) {
        //nacitam novu lokaciu
        _actualLocation.postValue(location)
        //prepnem fragment na detail
        actualFragmentId.postValue(R.id.location_detail)

        loadAndSaveDataForLocation(location)
    }

    fun switchToChooseLocation() {
        //prepnem na fragment vyberu lokacii
        actualFragmentId.postValue(R.id.location_choose)
    }
}