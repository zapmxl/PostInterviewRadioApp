package com.example.radiopostinterview3ds.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RadioStationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStations(stations: List<RadioStationEntity>)

    @Query("SELECT * FROM radio_stations")
    suspend fun getAllStations(): List<RadioStationEntity>

    @Query("SELECT * FROM radio_stations WHERE isFavorite = 1")
    suspend fun getFavoriteStations(): List<RadioStationEntity>

    @Query("UPDATE radio_stations SET isFavorite = :isFavorite WHERE id = :stationId")
    suspend fun updateFavoriteStatus(stationId: String, isFavorite: Boolean)
}
