package com.lazycouple.restapiclient.util

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView


abstract class BindingHolder<T>(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    abstract fun bind(variable: T)
}
