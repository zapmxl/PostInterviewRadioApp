package com.example.radiopostinterview3ds.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.radiopostinterview3ds.data.RadioStationEntity
import com.example.radiopostinterview3ds.ExoPlayerManager
import androidx.compose.ui.platform.LocalContext

@Composable
fun RadioStationItem(station: RadioStationEntity, onFavoriteToggle: (RadioStationEntity) -> Unit) {
    val context = LocalContext.current  // Get the context at the beginning
    var exoPlayerManager by remember { mutableStateOf<ExoPlayerManager?>(null) }
    var isPlaying by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = station.title ?: "Unknown Station", style = MaterialTheme.typography.titleMedium)
            Text(text = station.description ?: "No description available")

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Button(onClick = {
                    if (isPlaying) {
                        exoPlayerManager?.pause()
                    } else {
                        if (exoPlayerManager == null) {
                            exoPlayerManager = ExoPlayerManager(context)  // Use the context here
                        }
                        exoPlayerManager?.playStream(station.streaming_url ?: "") // Provide empty string if null
                    }
                    isPlaying = !isPlaying
                }) {
                    Text(text = if (isPlaying) "Pause" else "Play")
                }

                Button(onClick = { onFavoriteToggle(station) }) {
                    Text(text = if (station.isFavorite) "Remove Favorite" else "Favorite")
                }
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            exoPlayerManager?.release()
        }
    }
}
