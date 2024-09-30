package com.example.radiopostinterview3ds.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.radiopostinterview3ds.data.RadioStationEntity

@Composable
fun RadioStationItem(
    station: RadioStationEntity,
    onFavoriteToggle: (RadioStationEntity) -> Unit,
    currentPlayingStation: RadioStationEntity?,
    onPlay: (RadioStationEntity) -> Unit,
    onStop: () -> Unit
) {
    val isPlaying = currentPlayingStation == station

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = station.title ?: "Unknown Station", style = MaterialTheme.typography.titleMedium)
            Text(text = station.description ?: "No description available")

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Button(onClick = {
                    if (isPlaying) {
                        onStop() // Call stop if currently playing
                    } else {
                        onPlay(station) // Call play if not playing
                    }
                }) {
                    Text(text = if (isPlaying) "Stop" else "Play") // Change button text based on state
                }

                Button(onClick = { onFavoriteToggle(station) }) {
                    Text(text = if (station.isFavorite) "Remove Favorite" else "Favorite")
                }
            }
        }
    }
}
