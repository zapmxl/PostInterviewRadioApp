package com.example.radiopostinterview3ds.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.radiopostinterview3ds.RadioStationViewModel

@Composable
fun MainScreen(
    viewModel: RadioStationViewModel,
    searchText: String
) {
    // Collecting the StateFlow directly
    val filteredStations by viewModel.filterStations(searchText).collectAsState(emptyList()) // Collect filtered stations
    val currentPlayingStation by viewModel.currentPlayingStation.collectAsState() // Collect current playing station

    if (filteredStations.isEmpty()) {
        Text("No stations available")
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
        ) {
            items(filteredStations.size) { index ->
                val station = filteredStations[index]
                RadioStationItem(
                    station = station,
                    onFavoriteToggle = { viewModel.toggleFavorite(station) },
                    currentPlayingStation = currentPlayingStation,
                    onPlay = { newStation ->
                        viewModel.playStation(newStation) // Play station
                    },
                    onStop = {
                        viewModel.stopPlaying() // Stop the current station
                    }
                )
            }
        }
    }
}
