package com.example.order.mvvmdemo.widget

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.example.order.mvvmdemo.R
import com.example.order.mvvmdemo.bean.Province
import com.example.order.mvvmdemo.databinding.ProvinceItemBinding
import com.example.order.mvvmdemo.utils.GsonHelper
import com.example.order.mvvmdemo.utils.LogUtil


/**
 * Created by lh, 2020-10-13
 */
class ProvinceAdapter(lists: List<Province>?, context: FragmentActivity) : RecyclerView.Adapter<ProvinceAdapter.ProvinceViewHolder>() {

    private val lists:List<Province>? = lists
    private val context: FragmentActivity = context

    private var clickListener: ItemClickListener ?= null

    fun setOnClickListener(clickListener: ItemClickListener?) {
        this.clickListener = clickListener
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProvinceViewHolder {
        val inflater = LayoutInflater.from(context.baseContext)
        val binding = DataBindingUtil.inflate<ProvinceItemBinding>(inflater, R.layout.province_item, parent, false)
        return ProvinceViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return lists?.size ?: 0
    }

    override fun onBindViewHolder(holder: ProvinceViewHolder, position: Int) {
        val binding = DataBindingUtil.getBinding<ProvinceItemBinding>(holder.itemView)
        binding?.viewModel = ViewModelProviders.of(context).get("".plus(position), ProvinceItemViewModel::class.java)
        LogUtil.d("onBindViewHolder: $position, binding:$binding, viewModel:${binding?.viewModel}")

        binding?.viewModel?.setName(lists?.get(position)?.name?:"")
        binding?.viewModel?.position?.value = position
        binding?.itemClickListener =  clickListener
    }


    class ProvinceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    }

}