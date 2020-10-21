package com.example.cassoviacoders.ui

import androidx.lifecycle.*
import com.example.cassoviacoders.LocationsRepo
import com.example.cassoviacoders.R
import com.example.cassoviacoders.db.*
import com.example.cassoviacoders.retrofit.APIController
import com.example.cassoviacoders.utils.FormatHelper
import com.example.cassoviacoders.utils.formatForSearch
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {
    //hodnota vyhladavacieho pola
    val filterString = MutableLiveData<String>()

    //zoznam odifltrovanych lokacii
    private val _filteredLocations = MediatorLiveData<List<LocationPojo>>()
    val filteredLocations: LiveData<List<LocationPojo>> = _filteredLocations

    //zoznam vsetkych lokacii v databaze
    val loadedLocations: LiveData<List<LocationPojo>> = LocationsRepo()
        .getDefaultLocationsLiveData()

    //aktualne zobrazena lokacia
    private val _actualLocation = MutableLiveData<Location?>()
    val actualLocation: LiveData<Location?> = _actualLocation

    val currentWeather: LiveData<CurrentWeather>

    //predpoved na zajtra
    val dailyWeather1: LiveData<DailyWeather>

    //predpoved na pozajtra
    val dailyWeather2: LiveData<DailyWeather>

    //predpoved na popozajtra
    val dailyWeather3: LiveData<DailyWeather>

    fun filterLocations(list: List<LocationPojo>?, filterString: String?): List<LocationPojo> {
        return list?.filter { t: LocationPojo ->
            t.location.title.formatForSearch().contains(filterString.formatForSearch())
        }?.toList() ?: emptyList()
    }

    init {
        _filteredLocations.postValue(LocationsRepo().getDefaultLocations())

        //aby sa refreshli lokacie pre zmene filtra
        _filteredLocations.addSource(filterString) {
            _filteredLocations.postValue(filterLocations(loadedLocations.value, it))
        }
        //aby sa refreshli lokacie pre zmene filtra
        _filteredLocations.addSource(loadedLocations) {
            _filteredLocations.postValue(filterLocations(it, filterString.value))
        }

        //aktualne pocasie sa nacitava z db podla aktualnej lokacie
        currentWeather = Transformations.switchMap(actualLocation) {
            it?.let {
                val currentDay = FormatHelper.getDay()
                //live data pre aktualnu predpovet pre zvolenu lokaciu a aktualny den
                WeatherDatabase.getInstance().currentWeatherDao()
                    .getCurrentWeatherForLocationLiveData(it.locId, currentDay)
            }
        }

        //aktualne pocasie sa nacitava z db podla aktualnej lokacie
        val currentDay = FormatHelper.getDay()
        //predpovede na nasledujuce dni
        dailyWeather1 = initDailyWeather(currentDay + 1)
        dailyWeather2 = initDailyWeather(currentDay + 2)
        dailyWeather3 = initDailyWeather(currentDay + 3)
    }

    //nacitam livedata pre kazdu predpoved dalsieho dna
    private fun initDailyWeather(day: Int): LiveData<DailyWeather> {
        return Transformations.switchMap(actualLocation) {
            it?.let {
                //live data pre aktualnu predpovet pre zvolenu lokaciu a aktualny den
                WeatherDatabase.getInstance().dailyWeatherDao()
                    .getDailyWeatherForLocation(it.locId, day)
            }
        }
    }

    //Nacita data z webu, pretransformuje na nase db objkekty a ulozi do db.
    private fun loadAndSaveDataForLocation(location: LocationPojo) {
        CoroutineScope(Dispatchers.IO).launch {
            val currentWeather = APIController().currentWeatherForLocation(location.location)
            currentWeather?.let {
                WeatherDatabase.getInstance().currentWeatherDao().insert(it)
            }

            //dostanem z api moje db objekty dennych predpovedi
            val weatherWithLocations = APIController().dailyWeatherForLocation(location.location)
            weatherWithLocations?.let {
                //vlozim predpovede pre den do databazy
                WeatherDatabase.getInstance().dailyWeatherDao().insertAll(it)
            }
        }
    }


    //    NAVIGACIA:
    val actualFragmentId = MutableLiveData<Int?>()

    //prepnut na fragment detailu
    fun switchToLocationDetail(location: LocationPojo) {
        //nacitam novu lokaciu
        _actualLocation.postValue(location.location)
        //prepnem fragment na detail
        actualFragmentId.postValue(R.id.location_detail)

        loadAndSaveDataForLocation(location)
    }

    //prepnut na fragment vyberu lokacie
    fun switchToChooseLocation() {
        //prepnem na fragment vyberu lokacii
        actualFragmentId.postValue(R.id.location_choose)
    }
}