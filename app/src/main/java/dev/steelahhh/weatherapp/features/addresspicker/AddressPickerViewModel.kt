package dev.steelahhh.weatherapp.features.addresspicker

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import dev.steelahhh.weatherapp.data.LocationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/*
 * Author: steelahhh
 * 14/8/20
 */

class AddressPickerViewModel @ViewModelInject constructor(
    private val locationRepository: LocationRepository
) : ViewModel() {
    sealed class State {
        object Unchanged : State()
        data class PickedNewAddress(val latitude: Double, val longitude: Double) : State()
    }

    private val _state: MutableStateFlow<State> = MutableStateFlow(State.Unchanged)
    val state: StateFlow<State> = _state

    fun onAddressPicked() {
        when (val value = state.value) {
            State.Unchanged -> Unit // do nothing here
            is State.PickedNewAddress -> {
                locationRepository.saveLatLng(value.latitude, value.longitude)
            }
        }
    }

    fun getCurrentLocation(): LatLng {
        val (lat, lng) = locationRepository.getLatLng()
        return LatLng(lat, lng)
    }

    fun onCameraMoved(latitude: Double, longitude: Double) {
        _state.value = State.PickedNewAddress(latitude, longitude)
    }
}
