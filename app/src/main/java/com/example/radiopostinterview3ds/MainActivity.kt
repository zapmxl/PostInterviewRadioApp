package com.example.radiopostinterview3ds

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material3.*
import androidx.lifecycle.ViewModelProvider
import com.example.radiopostinterview3ds.data.RadioStationDatabase
import com.example.radiopostinterview3ds.repository.RadioStationRepository
import com.example.radiopostinterview3ds.ui.theme.FavoritesScreen
import com.example.radiopostinterview3ds.ui.theme.MainScreen
import com.example.radiopostinterview3ds.ui.theme.RadioPostInterview3dsTheme
import com.example.radiopostinterview3ds.utils.NetworkMonitor

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: RadioStationViewModel
    private lateinit var networkMonitor: NetworkMonitor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        networkMonitor = NetworkMonitor(this)

        val database = RadioStationDatabase.getDatabase(this)
        val repository = RadioStationRepository(database.radioStationDao(), this)
        val factory = RadioStationViewModelFactory(repository)

        viewModel = ViewModelProvider(this, factory).get(RadioStationViewModel::class.java)

        networkMonitor.observe(this) { isConnected ->
            if (isConnected == true) {
                viewModel.fetchStations()
            }
        }

        setContent {
            var isDarkTheme by remember { mutableStateOf(false) }
            RadioPostInterview3dsTheme(darkTheme = isDarkTheme) {
                MyApp(viewModel = viewModel, isConnected = networkMonitor, isDarkTheme = isDarkTheme) {
                    isDarkTheme = !isDarkTheme  // Toggle theme
                }
            }
        }
    }
}

@Composable
fun MyApp(viewModel: RadioStationViewModel, isConnected: NetworkMonitor, isDarkTheme: Boolean, onThemeToggle: () -> Unit) {
    // Entire screen background using Surface
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background // Apply the background color dynamically
    ) {
        var showFavorites by remember { mutableStateOf(false) }

        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            if (isConnected.value == false) {
                Text(
                    text = "You are offline. Showing cached data.",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            // Buttons in the same row
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(onClick = { showFavorites = !showFavorites }) {
                    Text(text = if (showFavorites) "Show All Stations" else "Show Favorites")
                }

                Spacer(modifier = Modifier.weight(1f)) // Spacer to push the theme button to the right

                Button(
                    onClick = { onThemeToggle() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(text = if (isDarkTheme) "Light Mode" else "Dark Mode")
                }
            }

            // Content area
            if (showFavorites) {
                FavoritesScreen(viewModel = viewModel)
            } else {
                MainScreen(viewModel = viewModel)
            }
        }
    }
}
