package com.amuyu.testrestapi

import android.content.Context
import com.amuyu.logger.DefaultLogPrinter
import com.amuyu.logger.Logger


/**
 * Created by noco on 2016-10-12.
 */
object TestRestApi {

    fun init(context: Context) {
        Logger.addLogPrinter(DefaultLogPrinter(context))
    }

    fun test(): Int? {
        var temp: String? = null
//        val size = temp?.length ?: -1
        val size = temp?.length
        return size
    }
}
