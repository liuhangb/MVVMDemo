package com.example.order.mvvmdemo.widget

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
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
            recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
            recyclerView.adapter = adapter
            recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL))
        }
    }
}