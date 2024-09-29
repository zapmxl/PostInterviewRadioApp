package com.example.radiopostinterview3ds

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import com.example.radiopostinterview3ds.ui.theme.FavoritesScreen
import com.example.radiopostinterview3ds.ui.theme.MainScreen

class MainActivity : ComponentActivity() {

    private val viewModel: RadioStationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Fetch data from the API
        viewModel.fetchStations()

        setContent {
            // State to handle screen switching between MainScreen and FavoritesScreen
            var showFavorites by remember { mutableStateOf(false) }

            Column {
                // Toggle between All Stations and Favorites
                Button(onClick = { showFavorites = !showFavorites }) {
                    Text(text = if (showFavorites) "Show All Stations" else "Show Favorites")
                }

                if (showFavorites) {
                    // Show Favorites Screen
                    FavoritesScreen(viewModel = viewModel)
                } else {
                    // Show Main Screen with All Stations
                    MainScreen(viewModel = viewModel)
                }
            }
        }
    }
}
