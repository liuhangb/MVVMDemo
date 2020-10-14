package com.example.order.mvvmdemo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.order.mvvmdemo.bean.Province
import com.example.order.mvvmdemo.databinding.ActivityMainBinding
import com.example.order.mvvmdemo.net.ApiService
import com.example.order.mvvmdemo.net.RequestClient
import com.example.order.mvvmdemo.utils.GsonHelper
import com.example.order.mvvmdemo.utils.LogUtil
import com.example.order.mvvmdemo.widget.ItemClickListener
import com.example.order.mvvmdemo.widget.ProvinceAdapter
import com.example.order.mvvmdemo.widget.ProvinceViewModel
import kotlinx.coroutines.*

/**
 */
class MainActivity : AppCompatActivity() {

    private val viewModel by lazy { ViewModelProviders.of(this).get(ProvinceViewModel::class.java) }
    private val scope = CoroutineScope(Job() + Dispatchers.Main)

    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(
            this,
            R.layout.activity_main
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        scope.launch {
            val provinces = getProvinces()
            LogUtil.d("test", "provinces: " + GsonHelper.toString(provinces))
            val provinceAdapter = ProvinceAdapter(provinces, this@MainActivity)
            viewModel.setAdapter(provinceAdapter)
            provinceAdapter.setOnClickListener(object : ItemClickListener {
                override fun onClick(position: Int) {
                    LogUtil.d("onClick:$position")
                    val intent = Intent(this@MainActivity, WeatherActivity::class.java)
                    this@MainActivity.startActivity(intent)
                }
            })
        }

    }

    private suspend fun getProvinces(): List<Province>? {
        return withContext(Dispatchers.IO) {
            val provinces = RequestClient.apiService().getProvinces()
            val weather = RequestClient.apiService().getWeather(ApiService.weatherUrl)
            val execute1 = weather.execute()
            LogUtil.d("weather: ${GsonHelper.toString(execute1.body())}")
            val execute = provinces.execute()
            val result: List<Province>? = execute.body()?:null
            result
        }
    }
}
