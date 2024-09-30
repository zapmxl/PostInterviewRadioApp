# Radio Station App

A simple Radio Station App built using Kotlin that allows users to browse radio stations fetched from an API, search for stations by name, and mark their favorite stations. The app also supports playing radio streams using ExoPlayer, handles offline usage, and includes error handling for failed API calls.

## Features

### 1. API Integration
- Connects to a provided radio stations API to fetch a list of radio stations.
- Displays radio station details such as station name, description, and stream URL.
- Implements a search feature allowing users to filter stations by name.

### 2. Local Data Handling
- Stores radio station data locally using Room, allowing the app to display stations even without an internet connection.
- Users can mark stations as favorites, and this information is stored locally.

### 3. User Interface
- Built using Jetpack Compose for a modern and user-friendly UI.
- Includes a main screen displaying a list of stations, with details such as station name and description.
- Provides a view for each station with options to mark it as a favorite.
- Includes a “Favorites” tab where users can view their favorite stations.

### 4. Bonus Features
- Integrated ExoPlayer to enable users to play the radio station streams.
- Implements pagination/lazy loading when fetching large datasets from the API.
- Includes error handling for failed API calls or network issues, with informative Toast messages.

## Tech Stack

- **Language**: Kotlin
- **UI**: Jetpack Compose
- **Networking**: Retrofit + GSON
- **Local Database**: Room
- **Streaming**: ExoPlayer
- **State Management**: ViewModel + StateFlow
- **Dependency Injection**: Kotlin DSL for Gradle configuration
- **Offline Support**: Caching with Room

## Setup and Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/zapmxl/PostInterviewRadioApp.git
   cd PostInterviewRadioApp   
2. Open the project in Android Studio.
3. Add a .env file at the root of the project to provide the API_BASE_URL.
4. Sync the project with Gradle files.
5. Build and run the app on an emulator or a physical device.

###Code Structure

##Project Structure

  ├── data
  │   ├── RadioStationDatabase.kt   # Database setup
  │   ├── RadioStationEntity.kt     # Entity definition for Room
  │   ├── RadioStationDao.kt        # DAO for Room database
  │   ├── RadioStationApi.kt        # Retrofit API service
  │   ├── RetrofitInstance.kt       # Singleton instance of Retrofit
  ├── repository
  │   ├── RadioStationRepository.kt # Repository to handle data operations
  ├── ui
  │   ├── theme
  │   │   ├── MainScreen.kt         # Main screen layout
  │   │   ├── FavoritesScreen.kt    # Favorites screen layout
  │   │   ├── RadioStationItem.kt   # UI component for individual stations
  ├── utils
  │   ├── NetworkMonitor.kt         # Monitor network connectivity
  │   ├── NetworkUtils.kt           # Utility functions for network operations
  ├── ExoPlayerManager.kt           # Manager for ExoPlayer setup
  ├── MainActivity.kt               # Main entry point of the application
  ├── RadioStationViewModel.kt      # ViewModel for managing UI state
  ├── RadioStationViewModelFactory.kt # Factory for ViewModel

###Highlights of Key Files

 - MainActivity.kt: Hosts the main UI of the application using Jetpack Compose.
 - RadioStationViewModel.kt: Manages the state of radio stations and controls playback using ExoPlayer.
 - RadioStationRepository.kt: Handles data fetching from both the API and the local Room database.
 - ExoPlayerManager.kt: Manages audio playback functionality using ExoPlayer.
 - NetworkMonitor.kt & NetworkUtils.kt: Provides network monitoring and utility functions to detect online/offline status.

###UI Components

 - MainScreen.kt: Displays the list of all radio stations fetched from the API or local database.
 - FavoritesScreen.kt: Displays the list of favorite stations.
 - RadioStationItem.kt: Represents individual radio station items, showing options to play/stop and mark as favorite.

###How It Works

 - The app fetches radio stations from the API upon launch and stores them in a local Room database.
 - Users can search for stations, mark them as favorites, and play/pause radio streams using ExoPlayer.
 - The app handles network connectivity changes gracefully, allowing offline access to cached data.
 - A StateFlow-driven ViewModel ensures seamless state management and updates to the UI.

### How It Works
 
 - The app fetches radio stations from the API upon launch and stores them in a local Room database.
 - Users can search for stations, mark them as favorites, and play/pause radio streams using ExoPlayer.
 - The app handles network connectivity changes gracefully, allowing offline access to cached data.
 - A StateFlow-driven ViewModel ensures seamless state management and updates to the UI.

###How to Test

 - API Functionality: Launch the app to verify that it fetches data from the API.
 - -Offline Functionality: Disconnect the internet and relaunch the app to test cached data.
 - Play/Stop Functionality: Test multiple radio stations to verify seamless playback and state changes.
 - Search Functionality: Use the search bar to filter radio stations by name.

###Possible Future Improvements
 - Implement advanced pagination with more complex datasets.
 - Add playback controls (e.g., next, previous, seek) for radio streams.
 - Enhance error messages with detailed user guidance.

###Conclusion
The Radio Station App provides a simple yet complete solution for browsing, searching, and playing radio stations with offline support. The combination of Jetpack Compose, Retrofit, Room, and ExoPlayer delivers a smooth and modern user experience.

Feel free to reach out for any questions or suggestions regarding this project!

Author: Michail Mallis

GitHub: zapmxl
