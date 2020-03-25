package com.raghu.cityweather.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raghu.cityweather.R
import com.raghu.cityweather.apis.RetrofitClient
import com.raghu.cityweather.models.CityWeather
import com.raghu.cityweather.repos.CityWeatherRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CityWeatherViewModel(private val retrofitClient: RetrofitClient) : ViewModel() {

    val name = MutableLiveData<String>()

    val temperature = MutableLiveData<String>()

    val climate = MutableLiveData<Int>()

    val climateImage = MutableLiveData<Int>()

    var repository:CityWeatherRepo = CityWeatherRepo(retrofitClient)

    fun loadCityWeathers(context: Context) {

        viewModelScope.launch {
            try {
                val response = repository.getCityWeather()
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        Log.e(TAG, "getCityWeather Success")
                        updateData(response.body())
                    } else {
                        Log.e(TAG, "getCityWeather failed: \n ${response.errorBody()?.toString()}")
                        updateData(CityWeather("-", -1, 0))
                    }
                }
            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    updateData(CityWeather("-", -1, 0))
                }
                Log.e(TAG, "getCityWeather failed: \n ${ex.message}")
            }
        }
    }

    private fun updateData(cityWeather : CityWeather?) {
        name?.let {
            name.value = cityWeather!!.city

            if(cityWeather!!.temperature != -1)
                temperature.value = "${cityWeather!!.temperature}°c"
            else
                temperature.value = "-°c"

            climate.value = getClimate(cityWeather!!.climateCode)
            climateImage.value = getClimateImage(cityWeather!!.climateCode)
        }
    }

    private fun getClimate(climateCode: Int): Int {

        when(climateCode) {
            1 -> return R.string.sunny
            2 -> return R.string.rainy
            3 -> return R.string.cloudy
            4 -> return R.string.windy
            else -> return R.string.na
        }
    }

    private fun getClimateImage(climateCode: Int): Int {

        when(climateCode) {
            1 -> return R.drawable.sunny
            //2 -> return R.drawable.rainy //Image unavailable
            //3 -> return R.drawable.cloudy //Image unavailable
            //4 -> return R.drawable.windy //Image unavailable
            else -> return 0
        }
    }

    companion object {
        private const val TAG = "CityWeatherViewModel"
    }

}