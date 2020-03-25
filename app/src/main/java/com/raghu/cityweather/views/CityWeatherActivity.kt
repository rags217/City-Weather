package com.raghu.cityweather.views

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.raghu.cityweather.R
import com.raghu.cityweather.databinding.ActivityCityWeatherBinding
import com.raghu.cityweather.viewmodels.CityWeatherViewModel
import kotlinx.android.synthetic.main.activity_city_weather.*
import org.koin.android.ext.android.inject

class CityWeatherActivity : AppCompatActivity() {

    private val viewModel: CityWeatherViewModel by inject()
    private lateinit var binding: ActivityCityWeatherBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_city_weather)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        progress_bar.visibility = View.VISIBLE
        viewModel.loadCityWeathers(this)

        viewModel.climate.observe(this, Observer {
            it?.let { climateResID ->
                 climate!!.setText(climateResID)
            }
        })

        viewModel.climateImage.observe(this, Observer {
            it?.let { climateImage ->
                climate_image!!.setImageResource(climateImage)
                progress_bar.visibility = View.GONE
                if(climateImage == 0)
                    Toast.makeText(this, R.string.error_toast, Toast.LENGTH_SHORT).show()
            }
        })
    }
}
