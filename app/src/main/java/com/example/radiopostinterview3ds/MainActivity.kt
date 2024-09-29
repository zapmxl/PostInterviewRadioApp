package com.example.radiopostinterview3ds

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import com.example.radiopostinterview3ds.data.RadioStationDatabase
import com.example.radiopostinterview3ds.repository.RadioStationRepository
import com.example.radiopostinterview3ds.ui.theme.FavoritesScreen
import com.example.radiopostinterview3ds.ui.theme.MainScreen

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: RadioStationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create the repository and pass it to the ViewModelFactory
        val database = RadioStationDatabase.getDatabase(this)
        val repository = RadioStationRepository(database.radioStationDao(), this)
        val factory = RadioStationViewModelFactory(repository)

        // Use the factory to create the ViewModel
        viewModel = ViewModelProvider(this, factory).get(RadioStationViewModel::class.java)

        // Call fetchStations to fetch data from API or Room database
        viewModel.fetchStations()

        // Set content using Jetpack Compose
        setContent {
            MyApp(viewModel = viewModel)
        }
    }
}

@Composable
fun MyApp(viewModel: RadioStationViewModel) {
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
