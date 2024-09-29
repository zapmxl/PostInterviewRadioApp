package com.example.radiopostinterview3ds

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.radiopostinterview3ds.repository.RadioStationRepository

class RadioStationViewModelFactory(
    private val repository: RadioStationRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RadioStationViewModel::class.java)) {
            return RadioStationViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
