package com.example.radiopostinterview3ds.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RadioStationApi {

    @GET("stations/{station_id}")  // Use the endpoint pattern from the API docs
    suspend fun getRadioStationDetails(@Path("station_id") stationId: String): Response<RadioStation>
}
