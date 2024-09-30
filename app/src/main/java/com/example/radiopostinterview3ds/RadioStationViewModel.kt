// RadioStationViewModel.kt
package com.example.radiopostinterview3ds

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.radiopostinterview3ds.data.RadioStationEntity
import com.example.radiopostinterview3ds.repository.RadioStationRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class RadioStationViewModel(
    private val repository: RadioStationRepository,
    private val context: Context // Pass the context to create ExoPlayerManager
) : ViewModel() {

    private val _stations = MutableStateFlow<List<RadioStationEntity>>(emptyList())
    val stations: StateFlow<List<RadioStationEntity>> = _stations.asStateFlow()

    private val _favoriteStations = MutableStateFlow<List<RadioStationEntity>>(emptyList())
    val favoriteStations: StateFlow<List<RadioStationEntity>> = _favoriteStations.asStateFlow()

    private var exoPlayerManager: ExoPlayerManager? = null // Store the player manager
    private var currentPlayingStation: RadioStationEntity? = null // Keep track of the currently playing station

    init {
        fetchStations()
    }

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

    fun toggleFavorite(station: RadioStationEntity) {
        viewModelScope.launch {
            repository.updateFavoriteStatus(station.station_id, !station.isFavorite)
        }
    }

    fun playStation(station: RadioStationEntity) {
        if (currentPlayingStation != null && currentPlayingStation != station) {
            stopPlaying() // Stop the current station if it's different
        }
        if (exoPlayerManager == null) {
            exoPlayerManager = ExoPlayerManager(context) // Create the ExoPlayerManager
        }
        exoPlayerManager?.playStream(station.streaming_url ?: "")
        currentPlayingStation = station // Update the currently playing station
    }

    fun stopPlaying() {
        exoPlayerManager?.stop()
        exoPlayerManager = null // Clear the player manager reference
        currentPlayingStation = null // Reset the currently playing station
    }

    fun filterStations(searchText: String): Flow<List<RadioStationEntity>> {
        return stations.map { stationList ->
            stationList.filter { it.title?.contains(searchText, ignoreCase = true) == true }
        }
    }
}
