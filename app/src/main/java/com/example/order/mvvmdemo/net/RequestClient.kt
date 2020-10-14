package com.example.order.mvvmdemo.net

import com.example.order.mvvmdemo.widget.StringConverterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by lh, 2020-10-13
 */
class RequestClient {
    companion object {

        private fun <T> getService(baseUrl: String, service: Class<T>): T {

            var clien = OkHttpClient.Builder()
                .build()

            val retrofit = Retrofit.Builder().baseUrl(baseUrl)
                //格式转换
                .addConverterFactory(StringConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(clien)
                .build()
            return retrofit.create(service)
        }

        //可用于多种不同种类的请求
        fun apiService(): ApiService {
            return getService(ApiService.baseUrl, ApiService::class.java)
        }

    }
}