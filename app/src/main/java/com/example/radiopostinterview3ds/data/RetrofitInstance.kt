package com.example.radiopostinterview3ds.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val BASE_URL = "https://public.radio.co/api/v2/"  // Ensure trailing slash

    val api: RadioStationApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RadioStationApi::class.java)
    }
}
