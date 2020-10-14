package com.example.order.mvvmdemo.widget

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.example.order.mvvmdemo.R
import com.example.order.mvvmdemo.bean.ForcastBean
import com.example.order.mvvmdemo.databinding.ForecastItemBinding
import com.example.order.mvvmdemo.databinding.ProvinceItemBinding
import com.example.order.mvvmdemo.utils.GsonHelper
import com.example.order.mvvmdemo.utils.LogUtil
import com.google.gson.Gson
import kotlinx.android.synthetic.main.forecast_item.view.*

/**
 * Created by lh, 2020-10-14
 */
class ForcastAdapter(lists: List<ForcastBean>, activity: FragmentActivity) :
    RecyclerView.Adapter<ForcastAdapter.ForecastViewHolder>() {

    private val lists = lists
    private val activity = activity

    init {
        LogUtil.d("lists: ${GsonHelper.toString(lists)}")
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val inflater = LayoutInflater.from(activity)
        val binding = DataBindingUtil.inflate<ForecastItemBinding>(inflater, R.layout.forecast_item, parent, false)
        return ForecastViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return lists?.size
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        val binding = DataBindingUtil.getBinding<ForecastItemBinding>(holder.itemView)
        val viewModel =
            ViewModelProviders.of(activity).get("".plus(position), ForeCastViewModel::class.java)
        binding?.viewModel = viewModel
        val forecastBean: ForcastBean? = lists?.get(position)
        LogUtil.d("position:$position, forecastBean:${forecastBean}")
        viewModel.date.value = forecastBean?.date
        viewModel.weather.value = forecastBean?.weather
        viewModel.max_temperature.value = forecastBean?.max_temperature
        viewModel.min_temperature.value = forecastBean?.min_temperature
    }

    class ForecastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}