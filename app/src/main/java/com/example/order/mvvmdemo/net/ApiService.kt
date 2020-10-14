package com.example.order.mvvmdemo.net

import com.example.order.mvvmdemo.bean.City
import com.example.order.mvvmdemo.bean.County
import com.example.order.mvvmdemo.bean.HeWeather
import com.example.order.mvvmdemo.bean.Province
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * Created by lh, 2020-10-13
 */
interface ApiService {

    companion object {
        //此类接口的基地址
        const val baseUrl = "http://guolin.tech/"
        const val weatherUrl = "http://www.fastmock.site/mock/9e451c292f28fd26dbb66be764662457/weather/city"
    }

    //请求类型 + 路由
    @GET("api/china")
    fun getProvinces(): Call<MutableList<Province>>


    @GET("api/china/{provinceId}")
    fun getCities(@Path("provinceId") provinceId: Int): Call<MutableList<City>>

    @GET("api/china/{provinceId}/{cityId}")
    fun getCounties(@Path("provinceId") provinceId: Int, @Path("cityId") cityId: Int): Call<MutableList<County>>

    @GET
    fun getWeather(@Url url: String): Call<HeWeather>

    @GET("api/bing_pic")
    fun getBingPic(): Call<String>
}