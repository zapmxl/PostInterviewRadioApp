package com.example.radiopostinterview3ds.repository

import com.example.radiopostinterview3ds.data.RadioStation
import com.example.radiopostinterview3ds.data.RetrofitInstance
import retrofit2.Response

class RadioStationRepository {

    suspend fun getRadioStationDetails(stationId: String): Response<RadioStation> {
        return RetrofitInstance.api.getRadioStationDetails(stationId)
    }
}
