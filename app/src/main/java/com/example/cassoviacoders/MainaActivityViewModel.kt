package com.example.cassoviacoders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cassoviacoders.utils.formatForSearch

class MainActivityViewModel : ViewModel() {
    val filterString = MutableLiveData<String>()

    private val _filteredLocations = MediatorLiveData<List<Location>>()
    val filteredLocations: LiveData<List<Location>> = _filteredLocations
    val loadedLocations: List<Location> = LocationsRepo().getDefaultLocations()

    private val _actualLocation = MutableLiveData<Location?>()
    val actualLocation = _actualLocation

    init {
        _filteredLocations.postValue(LocationsRepo().getDefaultLocations())

        _filteredLocations.addSource(filterString) {
            _filteredLocations.postValue(loadedLocations.filter { t: Location -> t.title.formatForSearch().contains(it.formatForSearch()) })
        }
    }

    fun switchToLocationDetail(location: Location) {
        _actualLocation.postValue(location)
    }

    fun switchToChooseLocation() {
        _actualLocation.postValue(null)
    }
}