package com.example.radiopostinterview3ds.data

import com.example.radiopostinterview3ds.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api: RadioStationApi by lazy {
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)  // Access the API_BASE_URL from BuildConfig
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RadioStationApi::class.java)
    }
}
