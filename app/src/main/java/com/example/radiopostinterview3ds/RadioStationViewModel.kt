package com.example.radiopostinterview3ds

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.radiopostinterview3ds.data.RadioStation
import com.example.radiopostinterview3ds.repository.RadioStationRepository
import kotlinx.coroutines.launch
import retrofit2.Response
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class RadioStationViewModel : ViewModel() {

    private val repository = RadioStationRepository()

    // MutableLiveData to hold the radio station details
    private val _radioStation = MutableLiveData<RadioStation?>()
    val radioStation: LiveData<RadioStation?> get() = _radioStation

    // MutableLiveData to handle errors
    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    // Function to fetch radio station details
    fun fetchRadioStationDetails(stationId: String) {
        viewModelScope.launch {
            val response = repository.getRadioStationDetails(stationId)
            if (response.isSuccessful) {
                _radioStation.value = response.body()
            } else {
                _error.value = "Failed to fetch station details"
            }
        }
    }
}
