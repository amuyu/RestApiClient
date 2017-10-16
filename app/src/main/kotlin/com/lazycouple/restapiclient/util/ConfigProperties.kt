package com.lazycouple.restapiclient.util

import android.content.Context

import com.lazycouple.restapiclient.R
import com.lazycouple.restapiclient.repository.local.model.Parameter

import java.util.ArrayList
import java.util.Arrays

/**
 * Created by noco on 2016-10-13.
 */
object ConfigProperties {
    private val TAG = ConfigProperties::class.java.simpleName

    fun getApiInfo(context: Context, arrayName: String): List<Parameter> {

        val id = context.resources.getIdentifier(arrayName, "array", context.packageName)
        val array = context.resources.getStringArray(id)

        val params = ArrayList<Parameter>()
        for (str in array) {
            val split = str.split("\\|".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            params.add(Parameter(split[0], split[1]))
        }
        return params
    }

    fun getHistories(context: Context): List<String> {
        val array = context.resources.getStringArray(R.array.history)
        val histories = ArrayList(Arrays.asList(*array))
        return histories
    }

}
