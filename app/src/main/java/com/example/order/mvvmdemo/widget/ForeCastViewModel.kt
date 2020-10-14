package com.example.order.mvvmdemo.widget

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by lh, 2020-10-14
 */
class ForeCastViewModel : ViewModel() {
    var date = MutableLiveData<String>()
    var weather = MutableLiveData<String>()
    var max_temperature = MutableLiveData<String>()
    var min_temperature = MutableLiveData<String>()


}