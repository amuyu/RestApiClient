package com.lazycouple.restapiclient.ui.adapter

import android.content.Context
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

import com.amuyu.logger.Logger
import com.android.databinding.library.baseAdapters.BR
import com.lazycouple.restapiclient.databinding.ReqParametersRowBinding
import com.lazycouple.restapiclient.db.model.Parameter
import com.lazycouple.restapiclient.ui.viewModel.RestRequestViewModel
import com.lazycouple.restapiclient.util.BindingHolder

/**
 * Created by noco on 2016-10-13.
 */
class ReqParamAdapter(private val mContext: Context, private val viewModel: RestRequestViewModel) : RecyclerView.Adapter<ReqParamAdapter.ParameterHolder>() {
    private val TAG = ReqParamAdapter::class.java.simpleName

    init {
        Logger.d("ReqParamAdapter")
        setHasStableIds(true)
    }

    fun getItem(position: Int): Parameter {
        return viewModel.getItem(position)
    }

    val items: List<Parameter>
        get() = viewModel.lists

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParameterHolder {
        Logger.d("onCreateViewHolder")
        val binding = ReqParametersRowBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return ParameterHolder(binding)
    }

    override fun onBindViewHolder(holder: ParameterHolder, position: Int) {

        val param = getItem(position)
        holder.bind(param)
        Logger.d("onBindViewHolder#posirion:" + position + "#param:" + param.toString())

    }

    override fun getItemCount(): Int {
        return viewModel.itemCount
    }

    override fun getItemId(position: Int): Long {
        return viewModel.getItemId(position)
    }

    class ParameterHolder(binding: ViewDataBinding) : BindingHolder<Parameter>(binding) {

        override fun bind(param: Parameter) {
            binding.setVariable(BR.param, param)
        }
    }
}
