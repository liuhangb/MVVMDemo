package com.example.order.mvvmdemo.widget

import retrofit2.Converter
import retrofit2.Retrofit
import okhttp3.ResponseBody
import okhttp3.RequestBody
import java.lang.reflect.Type


/**
 * Created by lh, 2020-10-14
 */
class StringConverterFactory : Converter.Factory() {


    companion object {
        val INSTANCE = StringConverterFactory()

        fun create(): StringConverterFactory {
            return INSTANCE
        }
    }


    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
        return if (type === String::class.java) {
            StringConverter.INSTANCE
        } else null    }
}