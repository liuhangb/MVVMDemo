package com.example.order.mvvmdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.order.annotation.TAG
import com.example.order.mvvmdemo.bean.DataX
import com.example.order.mvvmdemo.bean.ForcastBean
import com.example.order.mvvmdemo.bean.HeWeather
import com.example.order.mvvmdemo.databinding.ActivityWeatherBinding
import com.example.order.mvvmdemo.net.ApiService
import com.example.order.mvvmdemo.net.RequestClient
import com.example.order.mvvmdemo.tag.TagWeatherActivity
import com.example.order.mvvmdemo.widget.ForcastAdapter
import com.example.order.mvvmdemo.widget.WeatherViewModel
import kotlinx.coroutines.*
import okhttp3.Dispatcher

/**
 * Created by lh, 2020-10-14
 */
@TAG
class WeatherActivity: AppCompatActivity() {

    private val viewModel by lazy { ViewModelProviders.of(this).get(WeatherViewModel::class.java) }
    private val scope = CoroutineScope(Job() + Dispatchers.Main)

    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityWeatherBinding>(
            this,
            R.layout.activity_weather
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        scope.launch {
            val backgroundUrl = getBackgroundUrl()
            val heWeather = getWeather()

            val srcData = heWeather?.data
            val data:List<DataX>? = heWeather?.data?.data
            val list = ArrayList<ForcastBean>()
            if (data != null) {
                for (dataX in data) {
                    val forcastBean = ForcastBean()
                    forcastBean.date = dataX.date
                    forcastBean.weather = dataX.wea
                    forcastBean.max_temperature = dataX.tem1
                    forcastBean.min_temperature = dataX.tem2
                    list.add(forcastBean)
                }
            }
            binding.recyclerView.adapter = ForcastAdapter(list, this@WeatherActivity)
            binding.recyclerView.layoutManager = LinearLayoutManager(this@WeatherActivity)
//            viewModel.adapter.value = ForcastAdapter(list, this@WeatherActivity)
            viewModel.backgroundUrl.value = backgroundUrl
            viewModel.city.value =  srcData?.city
            viewModel.time.value = srcData?.update_time
            val today = data?.get(0)
            viewModel.temperature.value = today?.tem ?: ""
            viewModel.aoiIndex.value =  "90"
            viewModel.pmIndex.value = "140"
            viewModel.weather.value = today?.wea
        }
    }

    suspend fun getBackgroundUrl() : String? {
        return withContext(Dispatchers.IO){
            val backgroundUrl:String? = RequestClient.apiService().getBingPic().execute().body()
            backgroundUrl
        }
    }

    suspend fun getWeather(): HeWeather? {
       return withContext(Dispatchers.IO) {
           val call = RequestClient.apiService().getWeather(ApiService.weatherUrl)
           val heWeather = call.execute().body()
           heWeather
       }
    }
}