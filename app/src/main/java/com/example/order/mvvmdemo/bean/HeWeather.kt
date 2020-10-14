package com.example.order.mvvmdemo.bean

/**
 * Created by lh, 2020-10-13
 */
data class HeWeather(
    val code: Int,
    val `data`: Data
)

data class Data(
    val city: String,
    val cityid: String,
    val `data`: List<DataX>,
    val update_time: String
)

data class DataX(
    val air: Int,
    val air_level: String,
    val air_tips: String,
    val alarm: Alarm,
    val date: String,
    val day: String,
    val hours: List<Hour>,
    val index: List<Index>,
    val tem: String,
    val tem1: String,
    val tem2: String,
    val wea: String,
    val week: String,
    val win: List<String>,
    val win_speed: String
)

data class Alarm(
    val alarm_content: String,
    val alarm_level: String,
    val alarm_type: String
)

data class Hour(
    val day: String,
    val tem: String,
    val wea: String,
    val win: String,
    val win_speed: String
)

data class Index(
    val desc: String,
    val level: String,
    val title: String
)