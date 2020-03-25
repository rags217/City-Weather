package com.raghu.cityweather.apis

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    fun getService(): CityWeatherService {
        return Retrofit.Builder()
            .baseUrl(SERVER_END_POINT)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(CityWeatherService::class.java)
    }

    companion object {
        private const val SERVER_END_POINT = "http://www.mocky.io"
        private val gson = GsonBuilder()
            .setLenient()
            .create()
    }
}