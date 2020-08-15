package dev.steelahhh.weatherapp.features.home

import android.os.Bundle
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import coil.api.load
import dagger.hilt.android.AndroidEntryPoint
import dev.steelahhh.weatherapp.R
import dev.steelahhh.weatherapp.data.model.Weather
import dev.steelahhh.weatherapp.features.addresspicker.AddressPickerFragment.Companion.ADDRESS_REQUEST_ID
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.flow.collect

/*
 * Author: steelahhh
 * 14/8/20
 */

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private val vm: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupListeners()
        lifecycleScope.launchWhenStarted {
            vm.state.collect {
                when (it) {
                    HomeViewModel.State.Loading -> renderLoading()
                    is HomeViewModel.State.Content -> renderContent(it.weather)
                    is HomeViewModel.State.Error -> renderError(it.message)
                }
            }
        }
    }

    private fun setupListeners() {
        setFragmentResultListener(ADDRESS_REQUEST_ID) { key, bundle ->
            if (key == ADDRESS_REQUEST_ID) {
                // TODO: handle the picked address
            }
        }

        changeAddressButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addressPickerFragment)
        }
    }

    private fun renderLoading() {
        errorContainer.isGone = true
        contentContainer.isGone = true
        progress.isVisible = true
    }

    private fun renderContent(weather: Weather) {
        errorContainer.isGone = true
        contentContainer.isVisible = true
        progress.isGone = true
        locationTV.text = weather.location
        weatherIV.load(weather.icon) {
            crossfade(true)
        }
        weatherDescriptionTV.text = weather.description
        temperatureTv.text = weather.temperature
        humidityTv.text = weather.humidity
        windSpeedTv.text = weather.windSpeed
        pressureTv.text = weather.pressure
        uviTv.text = weather.uvIndex
    }

    private fun renderError(message: String) {
        // TODO: display error message
        contentContainer.isGone = true
        progress.isGone = true

        errorContainer.isVisible = true
        retryButton.setOnClickListener {
            vm.refresh()
        }
    }
}
