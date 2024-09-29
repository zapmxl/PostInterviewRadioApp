package com.example.radiopostinterview3ds.repository

import com.example.radiopostinterview3ds.data.RadioStationApi
import com.example.radiopostinterview3ds.data.RadioStationDao
import com.example.radiopostinterview3ds.data.RadioStationEntity
import com.example.radiopostinterview3ds.data.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class RadioStationRepository(
    private val stationDao: RadioStationDao
) {

    // Fetch stations from API and insert into Room database
    suspend fun fetchStationsFromApi() {
        withContext(Dispatchers.IO) {
            try {
                val response = RetrofitInstance.api.getRadioStations()
                if (response.isSuccessful) {
                    response.body()?.let { stations ->
                        stationDao.insertStations(stations)  // Save to Room database
                    }
                }
            } catch (e: Exception) {
                // Handle API error (e.g., log the error, show message, etc.)
                e.printStackTrace()
            }
        }
    }

    // Get all stations from Room database
    fun getStations(): Flow<List<RadioStationEntity>> = stationDao.getAllStations()

    // Get favorite stations
    fun getFavoriteStations(): Flow<List<RadioStationEntity>> = stationDao.getFavoriteStations()

    // Update favorite status
    suspend fun updateFavoriteStatus(id: String, isFavorite: Boolean) {
        stationDao.updateFavoriteStatus(id, isFavorite)
    }
}
