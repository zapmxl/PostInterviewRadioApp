package com.example.radiopostinterview3ds

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Text  // Import for Material3 Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState  // LiveData integration with Compose
import androidx.lifecycle.viewmodel.compose.viewModel  // ViewModel integration with Compose
import com.example.radiopostinterview3ds.ui.theme.RadioStationItem  // Import for RadioStationItem

class MainActivity : ComponentActivity() {

    private val viewModel: RadioStationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Fetch the radio station details for a specific station
        viewModel.fetchRadioStationDetails("your_station_id")

        // Set content view with Jetpack Compose
        setContent {
            MainScreen(viewModel)
        }
    }
}

@Composable
fun MainScreen(viewModel: RadioStationViewModel = viewModel()) {
    val radioStation = viewModel.radioStation.observeAsState()
    val error = viewModel.error.observeAsState()

    if (error.value != null) {
        Text(text = "Error: ${error.value}")
    } else if (radioStation.value != null) {
        RadioStationItem(station = radioStation.value!!, onFavoriteToggle = {})
    } else {
        Text(text = "Loading...")
    }
}
