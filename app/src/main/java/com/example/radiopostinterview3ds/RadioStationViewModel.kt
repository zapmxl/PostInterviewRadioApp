package com.example.radiopostinterview3ds

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.radiopostinterview3ds.data.RadioStationEntity
import com.example.radiopostinterview3ds.repository.RadioStationRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class RadioStationViewModel(
    private val repository: RadioStationRepository
) : ViewModel() {

    // Use StateFlow for stations and favorite stations
    private val _stations = MutableStateFlow<List<RadioStationEntity>>(emptyList())
    val stations: StateFlow<List<RadioStationEntity>> = _stations.asStateFlow()

    private val _favoriteStations = MutableStateFlow<List<RadioStationEntity>>(emptyList())
    val favoriteStations: StateFlow<List<RadioStationEntity>> = _favoriteStations.asStateFlow()

    init {
        // Fetch stations on initialization
        fetchStations()
    }

    // Fetch stations from repository
    fun fetchStations() {
        viewModelScope.launch {
            repository.getStations().collect { stationList ->
                _stations.value = stationList
            }
        }

        viewModelScope.launch {
            repository.getFavoriteStations().collect { favoriteList ->
                _favoriteStations.value = favoriteList
            }
        }
    }

    // Toggle favorite status for a station
    fun toggleFavorite(station: RadioStationEntity) {
        viewModelScope.launch {
            repository.updateFavoriteStatus(station.station_id, !station.isFavorite)
        }
    }

    // Filter stations based on search text
    fun filterStations(searchText: String): Flow<List<RadioStationEntity>> {
        return stations.map { stationList ->
            stationList.filter { it.title?.contains(searchText, ignoreCase = true) == true }
        }
    }
}
