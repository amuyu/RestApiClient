package com.lazycouple.restapiclient.ui.viewModel

import com.lazycouple.restapiclient.util.ObservableViewModel

/**
 * Created by amuyu on 2017. 6. 12..
 */

abstract class AdapterViewModel<T> : ObservableViewModel() {
    internal var lists: ArrayList<T> = ArrayList()

    fun getLists(): List<T> {
        return lists
    }

    fun addItem(item: T) {
        lists.add(item)
    }

    val itemCount: Int
        get() = lists.size

    fun getItemId(position: Int): Long {
        return lists[position]?.hashCode()?.toLong() ?: -1
    }

    fun getItem(position: Int): T {
        return lists[position]
    }

    fun clearLists() {
        lists.clear()
    }
}
