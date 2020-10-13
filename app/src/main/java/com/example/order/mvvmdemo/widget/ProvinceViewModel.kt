package com.example.order.mvvmdemo.widget

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by lh, 2020-10-13
 */
class ProvinceViewModel: ViewModel() {
    val adapter = MutableLiveData<ProvinceAdapter>()

    fun setAdapter(adapter: ProvinceAdapter) {
        this.adapter.value = adapter
    }
}