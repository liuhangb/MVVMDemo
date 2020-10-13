package com.example.order.mvvmdemo.widget

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.order.mvvmdemo.utils.LogUtil


/**
 * Created by lh, 2020-10-13
 */
class ProvinceBindingAdapter {
    companion object {
        @JvmStatic
        @BindingAdapter("bind:setAdapter")
        fun setAdapter(recyclerView: RecyclerView, adapter: ProvinceAdapter?) {
            LogUtil.d("setAdapter======")
            recyclerView.adapter = adapter
        }
    }

}