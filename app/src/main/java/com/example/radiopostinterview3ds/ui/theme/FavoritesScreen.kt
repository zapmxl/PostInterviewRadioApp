package com.example.radiopostinterview3ds.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.radiopostinterview3ds.RadioStationViewModel

@Composable
fun FavoritesScreen(viewModel: RadioStationViewModel, searchText: String) {
    val favoriteStations by viewModel.favoriteStations.collectAsState()

    // Filter favorite stations based on search text
    val filteredFavorites = favoriteStations.filter {
        it.title?.contains(searchText, ignoreCase = true) == true
    }

    if (filteredFavorites.isEmpty()) {
        Text("No favorite stations available")
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
        ) {
            items(filteredFavorites.size) { index ->
                val station = filteredFavorites[index]
                RadioStationItem(
                    station = station,
                    onFavoriteToggle = { viewModel.toggleFavorite(station) },
                    currentPlayingStation = viewModel.currentPlayingStation.collectAsState().value, // Observe current playing station
                    onPlay = { newStation ->
                        viewModel.playStation(newStation) // Call playStation with the new station
                    },
                    onStop = {
                        viewModel.stopPlaying() // Call stopPlaying when stopping
                    }
                )
            }
        }
    }
}
