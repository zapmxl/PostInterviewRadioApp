package com.example.radiopostinterview3ds

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.lifecycle.ViewModelProvider
import com.example.radiopostinterview3ds.data.RadioStationDatabase
import com.example.radiopostinterview3ds.repository.RadioStationRepository
import com.example.radiopostinterview3ds.ui.theme.FavoritesScreen
import com.example.radiopostinterview3ds.ui.theme.MainScreen

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: RadioStationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create the database and repository, pass the context for network checks
        val database = RadioStationDatabase.getDatabase(this)
        val repository = RadioStationRepository(database.radioStationDao(), this)  // Pass the context here

        // Pass the repository into the ViewModelFactory
        val factory = RadioStationViewModelFactory(repository)

        // Use the factory to create the ViewModel
        viewModel = ViewModelProvider(this, factory).get(RadioStationViewModel::class.java)

        // Fetch stations from the API or Room database
        viewModel.fetchStations()

        // Set content using Jetpack Compose
        setContent {
            MyApp(viewModel = viewModel)
        }
    }
}

@Composable
fun MyApp(viewModel: RadioStationViewModel) {
    // State to toggle between MainScreen and FavoritesScreen
    var showFavorites by remember { mutableStateOf(false) }

    Column {
        // Button to toggle between All Stations and Favorites
        Button(onClick = { showFavorites = !showFavorites }) {
            Text(text = if (showFavorites) "Show All Stations" else "Show Favorites")
        }

        // Display the correct screen based on the state
        if (showFavorites) {
            // Show Favorites Screen
            FavoritesScreen(viewModel = viewModel)
        } else {
            // Show Main Screen with all stations
            MainScreen(viewModel = viewModel)
        }
    }
}
