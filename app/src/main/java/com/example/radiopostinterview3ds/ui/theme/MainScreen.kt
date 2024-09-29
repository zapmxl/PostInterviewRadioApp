package com.example.radiopostinterview3ds.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.radiopostinterview3ds.data.RadioStation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(stations: List<RadioStation>, onFavoriteToggle: (RadioStation) -> Unit) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Radio Stations") }) }
    ) { innerPadding ->  // Apply padding to the content
        LazyColumn(
            modifier = Modifier.padding(innerPadding)  // Apply the padding here
        ) {
            items(stations.size) { index ->
                val station = stations[index]
                RadioStationItem(station = station, onFavoriteToggle = onFavoriteToggle)
            }
        }
    }
}

@Composable
fun RadioStationItem(station: RadioStation, onFavoriteToggle: (RadioStation) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = station.name, style = MaterialTheme.typography.titleMedium)
            Text(text = station.description)
            Image(
                painter = rememberImagePainter(data = station.imageUrl),
                contentDescription = null,
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Row {
                Button(onClick = { onFavoriteToggle(station) }) {
                    Text("Favorite")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    // Add a sample preview with fake data
}
