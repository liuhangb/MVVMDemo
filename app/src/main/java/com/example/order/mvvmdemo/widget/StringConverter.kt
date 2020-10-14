package com.example.order.mvvmdemo.widget

import okhttp3.ResponseBody
import retrofit2.Converter

/**
 * Created by lh, 2020-10-14
 */
class StringConverter: Converter<ResponseBody, String> {
    companion object {
        val INSTANCE = StringConverter()
    }

    override fun convert(value: ResponseBody): String? {
        return value.string()
    }
}