package com.raghu.cityweather.repos

import com.raghu.cityweather.apis.RetrofitClient

class CityWeatherRepo(private val retrofitClient: RetrofitClient) {

    suspend fun getCityWeather() = retrofitClient.getService().getCityWeather()

}