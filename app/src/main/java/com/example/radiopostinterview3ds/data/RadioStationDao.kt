package com.example.radiopostinterview3ds.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RadioStationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStations(stations: List<RadioStationEntity>)

    // Return Flow instead of LiveData
    @Query("SELECT * FROM radio_stations")
    fun getAllStations(): Flow<List<RadioStationEntity>>

    // Return Flow for favorite stations
    @Query("SELECT * FROM radio_stations WHERE isFavorite = 1")
    fun getFavoriteStations(): Flow<List<RadioStationEntity>>

    // Update favorite status by station_id
    @Query("UPDATE radio_stations SET isFavorite = :isFavorite WHERE station_id = :stationId")
    suspend fun updateFavoriteStatus(stationId: String, isFavorite: Boolean)
}
