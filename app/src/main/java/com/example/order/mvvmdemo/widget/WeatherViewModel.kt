package com.example.order.mvvmdemo.widget

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by lh, 2020-10-14
 */
class WeatherViewModel:ViewModel() {
    val backgroundUrl = MutableLiveData<String>()
    val city = MutableLiveData<String>()
    val time = MutableLiveData<String>()
    val temperature = MutableLiveData<String>()
    val weather = MutableLiveData<String>()
    val aoiIndex = MutableLiveData<String>()
    val pmIndex = MutableLiveData<String>()

}