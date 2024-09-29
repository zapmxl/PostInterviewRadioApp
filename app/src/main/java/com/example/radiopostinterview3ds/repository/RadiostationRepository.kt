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

    class RadioStationRepository(
        private val stationDao: RadioStationDao,
        private val context: Context  // Pass the context for checking network availability
    ) {

        // Fetch stations from API if online, otherwise use Room database
        suspend fun fetchStations() {
            withContext(Dispatchers.IO) {
                if (isNetworkAvailable(context)) {
                    try {
                        val response = RetrofitInstance.api.getRadioStations()
                        if (response.isSuccessful) {
                            response.body()?.let { stations ->
                                Log.d("RadioStationRepository", "API response: $stations")
                                stationDao.insertStations(stations)  // Save to Room database
                            }
                        } else {
                            Log.e("RadioStationRepository", "API call failed: ${response.errorBody()}")
                        }
                    } catch (e: Exception) {
                        Log.e("RadioStationRepository", "Exception during API call", e)
                    }
                } else {
                    Log.d("RadioStationRepository", "No network available, loading from cache")
                }
            }
        }


        // Get all stations from Room database
        fun getStations(): Flow<List<RadioStationEntity>> = stationDao.getAllStations()

        // Get favorite stations from Room database
        fun getFavoriteStations(): Flow<List<RadioStationEntity>> = stationDao.getFavoriteStations()

        // Update favorite status in Room database
        suspend fun updateFavoriteStatus(id: String, isFavorite: Boolean) {
            stationDao.updateFavoriteStatus(id, isFavorite)
        }
    }
