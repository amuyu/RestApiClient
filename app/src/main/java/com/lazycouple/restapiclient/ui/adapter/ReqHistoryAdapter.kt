package com.lazycouple.restapiclient.ui.adapter

import android.content.Context
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

import com.android.databinding.library.baseAdapters.BR
import com.lazycouple.restapiclient.databinding.ReqHistoryRowBinding
import com.lazycouple.restapiclient.db.model.Api
import com.lazycouple.restapiclient.ui.contract.RequestHistoryContract.Presenter
import com.lazycouple.restapiclient.ui.viewModel.RequestHistoryViewModel
import com.lazycouple.restapiclient.util.BindingHolder

/**
 * Created by noco on 2016-10-13.
 */
class ReqHistoryAdapter(private val mContext: Context,
                        private val viewModel: RequestHistoryViewModel, private var presenter: Presenter) : RecyclerView.Adapter<ReqHistoryAdapter.HistoryHolder>() {
    private val TAG = ReqHistoryAdapter::class.java.simpleName

    fun getItem(position: Int): Api {
        return viewModel.getItem(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryHolder {
        val binding = ReqHistoryRowBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return HistoryHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryHolder, position: Int) {
        val title = getItem(position).url
        val binding = holder.binding as ReqHistoryRowBinding
        binding.title = title
        binding.position = position
        binding.presenter = presenter
    }

    override fun getItemCount(): Int {
        return viewModel.itemCount
    }

    inner class HistoryHolder(binding: ViewDataBinding) : BindingHolder<String>(binding) {


        override fun bind(variable: String) {
            binding.setVariable(BR.title, variable)
        }
    }
}
