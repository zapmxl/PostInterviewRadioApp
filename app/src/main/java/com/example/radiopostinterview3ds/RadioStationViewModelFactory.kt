package com.example.radiopostinterview3ds

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.radiopostinterview3ds.repository.RadioStationRepository
import com.example.radiopostinterview3ds.RadioStationViewModel

class RadioStationViewModelFactory(
    private val repository: RadioStationRepository,
    private val context: Context // Add context here
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RadioStationViewModel::class.java)) {
            return RadioStationViewModel(repository, context) as T // Pass context here
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
