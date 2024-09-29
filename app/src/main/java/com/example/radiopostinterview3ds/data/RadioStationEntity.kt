package com.example.radiopostinterview3ds.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "radio_stations")
data class RadioStationEntity(
    @PrimaryKey val id: String,
    val name: String,
    val description: String,
    val imageUrl: String,
    val streamUrl: String,
    var isFavorite: Boolean = false  // New favorite field
)
