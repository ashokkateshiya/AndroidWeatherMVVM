package com.ashok.weatherapp.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashok.weatherapp.model.ListItem
import com.ashok.weatherapp.repository.WeatherRepository
import kotlinx.coroutines.launch

class WeatherViewModel @ViewModelInject constructor(
    weatherRepository: WeatherRepository
) : ViewModel() {

    var listOfItems: MutableLiveData<List<ListItem>> = MutableLiveData<List<ListItem>>()

    private var _darkMode = MutableLiveData<Boolean>()
    val darkMode: LiveData<Boolean>
        get() = _darkMode

    init {
        viewModelScope.launch {
            listOfItems.value= weatherRepository.refreshWeatherData(25.9000,57.2432,"METRIC")!!
        }
        _darkMode.value = false
    }



    //Navigate to detail
    private val _navigateToWeatherDetail = MutableLiveData<ListItem>()
    val navigateToWeatherDetail
        get() = _navigateToWeatherDetail

    fun onWeatherItemClicked(item: ListItem) {
        _navigateToWeatherDetail.value = item
    }

    fun onWeatherDetailNavigated() {
        _navigateToWeatherDetail.value = null
    }
}