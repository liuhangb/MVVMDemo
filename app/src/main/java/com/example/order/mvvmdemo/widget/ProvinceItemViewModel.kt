package com.example.order.mvvmdemo.widget

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.order.mvvmdemo.utils.LogUtil

/**
 * Created by lh, 2020-10-13
 */
class ProvinceItemViewModel : ViewModel() {
    var name = MutableLiveData<String>()
    val position = MutableLiveData<Int>()


    fun setName(name: String) {
        this.name.value = name
    }
}