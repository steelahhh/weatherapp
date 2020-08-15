package dev.steelahhh.weatherapp.features.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.steelahhh.weatherapp.data.GetWeatherUseCase
import dev.steelahhh.weatherapp.data.model.Weather
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/*
 * Author: steelahhh
 * 14/8/20
 */

class HomeViewModel @ViewModelInject constructor(
    private val getWeatherUseCase: GetWeatherUseCase
) : ViewModel() {

    sealed class State {
        object Loading : State()
        data class Content(val weather: Weather) : State()
        data class Error(val message: String) : State()
    }

    private val _state: MutableStateFlow<State> = MutableStateFlow(State.Loading)
    val state: StateFlow<State> = _state

    init {
        loadWeather()
    }

    fun refresh() {
        _state.value = State.Loading
        loadWeather()
    }

    private fun loadWeather() {
        viewModelScope.launch(Dispatchers.IO) {
            getWeatherUseCase.invoke().either(
                { _state.value = State.Error(it.localizedMessage ?: it.message.orEmpty()) },
                { _state.value = State.Content(it) }
            )
        }
    }
}
