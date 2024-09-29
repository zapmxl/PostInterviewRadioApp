package com.example.radiopostinterview3ds.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.radiopostinterview3ds.RadioStationViewModel
import com.example.radiopostinterview3ds.data.RadioStationEntity

@Composable
fun MainScreen(viewModel: RadioStationViewModel) {
    val stations = viewModel.stations.observeAsState(emptyList())

    if (stations.value.isEmpty()) {
        // Show a message if no stations are available
        Text("No stations available")
    } else {
        // Show the list of stations
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
        ) {
            items(stations.value.size) { index ->
                val station = stations.value[index]
                RadioStationItem(
                    station = station,
                    onFavoriteToggle = { viewModel.toggleFavorite(station) }
                )
            }
        }
    }
}


@Composable
fun RadioStationItem(station: RadioStationEntity, onFavoriteToggle: (RadioStationEntity) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.elevatedCardElevation(4.dp)  // Use CardDefaults for elevation
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = station.name, style = MaterialTheme.typography.titleMedium)
            Text(text = station.description)
            Button(
                onClick = { onFavoriteToggle(station) }  // Toggle favorite
            ) {
                Text(text = if (station.isFavorite) "Remove favorite" else "Favorite")
            }
        }
    }
}
