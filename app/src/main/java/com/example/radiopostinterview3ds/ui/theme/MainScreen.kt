package com.example.radiopostinterview3ds.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.radiopostinterview3ds.RadioStationViewModel
import com.example.radiopostinterview3ds.data.RadioStationEntity

@Composable
fun MainScreen(
    viewModel: RadioStationViewModel,
    searchText: String
) {
    // Collecting the StateFlow directly
    val stations by viewModel.stations.collectAsState(emptyList())
    val filteredStations by viewModel.filterStations(searchText).collectAsState(emptyList()) // Collect filtered stations
    var currentPlayingStation by remember { mutableStateOf<RadioStationEntity?>(null) }

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
                        // Stop the current playing station if it's different
                        if (currentPlayingStation != newStation) {
                            currentPlayingStation?.let {
                                viewModel.stopPlaying()  // Stop previous station
                            }
                            currentPlayingStation = newStation
                            viewModel.playStation(newStation)  // Start the new station
                        }
                    },
                    onStop = {
                        currentPlayingStation = null
                        viewModel.stopPlaying()  // Stop the current station
                    }
                )
            }
        }
    }
}
