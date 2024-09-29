package com.example.radiopostinterview3ds

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.radiopostinterview3ds.data.RadioStationEntity
import com.example.radiopostinterview3ds.repository.RadioStationRepository
import kotlinx.coroutines.launch

class RadioStationViewModel(
    private val repository: RadioStationRepository
) : ViewModel() {

    val stations: LiveData<List<RadioStationEntity>> = repository.getStations().asLiveData()

    val favoriteStations: LiveData<List<RadioStationEntity>> = repository.getFavoriteStations().asLiveData()

    fun fetchStations() {
        viewModelScope.launch {
            repository.fetchStations()
        }
    }

    fun toggleFavorite(station: RadioStationEntity) {
        viewModelScope.launch {
            repository.updateFavoriteStatus(station.id, !station.isFavorite)
        }
    }
}
