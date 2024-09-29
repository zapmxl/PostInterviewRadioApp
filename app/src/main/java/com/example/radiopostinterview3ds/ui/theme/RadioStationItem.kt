package com.example.radiopostinterview3ds.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.radiopostinterview3ds.data.RadioStationEntity

@Composable
fun RadioStationItem(station: RadioStationEntity, onFavoriteToggle: (RadioStationEntity) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Display fallback text for missing data
            Text(text = station.title ?: "Unknown Station", style = MaterialTheme.typography.titleMedium)
            Text(text = station.description ?: "No description available")
            Button(
                onClick = { onFavoriteToggle(station) }  // Toggle favorite
            ) {
                Text(text = if (station.isFavorite) "Remove Favorite" else "Favorite")
            }
        }
    }
}
