package com.example.radiopostinterview3ds.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "radio_stations")
data class RadioStationEntity(
    @PrimaryKey val station_id: String,  // Use station_id from the JSON
    val title: String?,  // Use title from the JSON
    val description: String?,
    val logo: String?,  // Use logo from the JSON (this is the imageUrl)
    val streaming_url: String?,  // Use streaming_url from the JSON
    val isFavorite: Boolean = false  // Local field for marking favorites
)
