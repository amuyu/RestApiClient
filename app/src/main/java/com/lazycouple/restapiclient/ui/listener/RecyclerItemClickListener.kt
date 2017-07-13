package com.lazycouple.restapiclient.ui.listener

import android.support.v7.widget.RecyclerView

interface RecyclerItemClickListener {
    fun onItemClick(adapter: RecyclerView.Adapter<*>, position: Int)
}
