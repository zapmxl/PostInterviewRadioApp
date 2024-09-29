package com.example.radiopostinterview3ds.ui.theme

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.example.radiopostinterview3ds.RadioStationViewModel

@Composable
fun FavoritesScreen(viewModel: RadioStationViewModel) {
    val favoriteStations = viewModel.favoriteStations.observeAsState(emptyList())

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(favoriteStations.value.size) { index ->
            val station = favoriteStations.value[index]
            RadioStationItem(
                station = station,
                onFavoriteToggle = { viewModel.toggleFavorite(station) }
            )
        }
    }
}
