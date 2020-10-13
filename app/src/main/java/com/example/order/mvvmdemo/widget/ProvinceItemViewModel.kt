package com.example.order.mvvmdemo.widget

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by lh, 2020-10-13
 */
class ProvinceItemViewModel: ViewModel() {
    var name = MutableLiveData<String>()

    fun setName(name: String) {
        this.name.value = name
    }
}