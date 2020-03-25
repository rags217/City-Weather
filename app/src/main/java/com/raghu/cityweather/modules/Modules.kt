package com.raghu.cityweather.modules

import com.raghu.cityweather.apis.RetrofitClient
import com.raghu.cityweather.viewmodels.CityWeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CityWeatherViewModel(get()) }
}

val networkingModule = module {
    single { RetrofitClient() }
}