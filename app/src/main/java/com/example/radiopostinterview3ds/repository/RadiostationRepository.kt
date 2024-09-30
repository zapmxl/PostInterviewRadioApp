package com.example.radiopostinterview3ds.repository

import android.content.Context
import com.example.radiopostinterview3ds.data.RadioStationDao
import com.example.radiopostinterview3ds.data.RadioStationEntity
import com.example.radiopostinterview3ds.data.RetrofitInstance
import com.example.radiopostinterview3ds.utils.isNetworkAvailable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import android.util.Log
import android.widget.Toast

class RadioStationRepository(
    private val stationDao: RadioStationDao,
    private val context: Context
) {
    suspend fun fetchStations() {
        withContext(Dispatchers.IO) {
            if (isNetworkAvailable(context)) {
                try {
                    val response = RetrofitInstance.api.getRadioStations()
                    if (response.isSuccessful) {
                        response.body()?.let { stations ->
                            Log.d("RadioStationRepository", "API response: $stations")
                            stationDao.insertStations(stations)
                        } ?: run {
                            showToast("No data received from the API.")
                        }
                    } else {
                        showToast("API call failed: ${response.errorBody()}")
                        Log.e("RadioStationRepository", "API call failed: ${response.errorBody()}")
                    }
                } catch (e: Exception) {
                    showToast("Exception during API call: ${e.message}")
                    Log.e("RadioStationRepository", "Exception during API call", e)
                }
            } else {
                showToast("No network available, loading from cache.")
                Log.d("RadioStationRepository", "No network available, loading from cache")
            }
        }
    }

    private fun showToast(message: String) {
        // This function runs on the main thread to show a toast message
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    // Get all stations as Flow
    fun getStations(): Flow<List<RadioStationEntity>> = stationDao.getAllStations()

    // Get favorite stations as Flow
    fun getFavoriteStations(): Flow<List<RadioStationEntity>> = stationDao.getFavoriteStations()

    // Update favorite status in Room database
    suspend fun updateFavoriteStatus(id: String, isFavorite: Boolean) {
        stationDao.updateFavoriteStatus(id, isFavorite)
    }
}
