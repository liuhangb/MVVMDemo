package com.example.order.mvvmdemo.widget

import android.graphics.Bitmap
import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.example.order.mvvmdemo.utils.LogUtil
import android.R.attr.resource
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import com.bumptech.glide.request.animation.GlideAnimation
import android.os.Build
import android.R.attr.resource




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

        @JvmStatic
        @BindingAdapter("bind:background")
        fun setBackground(view: View, url: String?) {
            if (url == null) return
            Glide.with(view.context).load(url).asBitmap().into(object :SimpleTarget<Bitmap>(view.width, view.height) {
                override fun onResourceReady(
                    resource: Bitmap?,
                    glideAnimation: GlideAnimation<in Bitmap>?
                ) {
                    val drawable = BitmapDrawable(resource)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        view.setBackground(drawable)    //设置背景
                    }
                }

            })
        }
    }
}