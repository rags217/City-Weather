package com.raghu.cityweather.apis

import com.raghu.cityweather.models.CityWeather
import retrofit2.Response
import retrofit2.http.GET

interface CityWeatherService {
    @GET("/v2/5e7aa359300000e5c9930cce")
    suspend fun getCityWeather(): Response<CityWeather>
}