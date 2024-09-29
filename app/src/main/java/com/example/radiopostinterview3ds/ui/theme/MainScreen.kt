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
fun MainScreen(viewModel: RadioStationViewModel, searchText: String) {
    val stations by viewModel.stations.collectAsState()

    // Filter stations based on the search text
    val filteredStations = stations.filter {
        it.title?.contains(searchText, ignoreCase = true) == true
    }

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
                    onFavoriteToggle = { viewModel.toggleFavorite(station) }
                )
            }
        }
    }
}
