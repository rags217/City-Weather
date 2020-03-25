package com.raghu.cityweather.application

import android.app.Application
import com.raghu.cityweather.modules.networkingModule
import com.raghu.cityweather.modules.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

@Suppress("unused")
class CityWeatherApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidLogger()
            androidContext(this@CityWeatherApplication)
            modules(listOf(
                viewModelModule,
                networkingModule
            ))
        }
    }
}