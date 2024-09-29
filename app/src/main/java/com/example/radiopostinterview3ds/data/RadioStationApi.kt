package com.example.radiopostinterview3ds.data

import retrofit2.Response
import retrofit2.http.GET

interface RadioStationApi {

    // Define the endpoint for fetching radio stations
    @GET("stations.json")
    suspend fun getRadioStations(): Response<List<RadioStationEntity>>
}
