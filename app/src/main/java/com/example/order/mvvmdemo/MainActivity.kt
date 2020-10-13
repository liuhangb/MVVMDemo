package com.example.order.mvvmdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.order.mvvmdemo.bean.Province
import com.example.order.mvvmdemo.databinding.ActivityMainBinding
import com.example.order.mvvmdemo.net.RequestClient
import com.example.order.mvvmdemo.utils.GsonHelper
import com.example.order.mvvmdemo.utils.LogUtil
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
        binding.viewModel = viewModel
        scope.launch {
            val provinces = getProvinces()
            LogUtil.d("test", "provinces: " + GsonHelper.toString(provinces))
            viewModel.setAdapter(ProvinceAdapter(provinces, this@MainActivity))
        }
    }

    private suspend fun getProvinces(): List<Province>? {
        return withContext(Dispatchers.IO) {
            val provinces = RequestClient.apiService().getProvinces()
            val execute = provinces.execute()
            val result: List<Province>? = execute.body()?:null
            result
        }
    }
}
