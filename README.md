# Radio Station App

This is a simple Radio Station App built using Kotlin, allowing users to browse radio stations fetched from an API, search for stations by name, and mark their favorite stations. The app also supports local storage for offline usage.

## Key Features

### 1. API Integration
- Connects to a provided radio stations API to fetch a list of radio stations.
- Displays radio station details such as station name, description, and stream URL.
- Implements a search feature allowing users to filter stations by name.

### 2. Local Data Handling
- Stores locally radio station data so that the app can display stations even without an internet connection.
- Enables users to mark stations as favorites, storing this information locally.

### 3. User Interface
- A user-friendly interface built using JetPack Compose.
- A main screen displaying a list of stations, including station names and their images.
- Each station has a view for more information and an option to mark it as a favorite.
- A “Favorites” tab allows users to view their favorite stations.

### 4. Bonus Features
- Users can play the radio station stream using ExoPlayer.
- Pagination or lazy loading is implemented when fetching large datasets from the API.
- Includes error handling for failed API calls or network issues.

## Project Structure

- **MainActivity.kt**: The entry point of the application where the main UI is set up.
- **ViewModel**: Manages UI-related data in a lifecycle-conscious way. It handles fetching and managing radio station data.
- **Repository**: Interfaces with the local database and the remote API to fetch and store radio station data.
- **Data Layer**: Contains the data classes (entities), DAO interfaces for database operations, and Retrofit service for API interactions.
- **UI Components**: Composable functions for building the app’s user interface, including screens for displaying stations and favorites.

## Usage

1. Clone the repository or download the ZIP file.
2. Open the project in Android Studio.
3. Build and run the application on an Android device or emulator.

## Installation
Ensure you have the following installed:
- Android Studio
- Kotlin
- Required dependencies as defined in the `build.gradle` files.

## Dependencies
The project uses the following libraries:
- Retrofit for API calls.
- Room for local database storage.
- ExoPlayer for audio streaming.
- Jetpack Compose for building UI.

## Conclusion
This project demonstrates a comprehensive approach to building a radio station app using modern Android development practices. Feel free to explore and contribute!

