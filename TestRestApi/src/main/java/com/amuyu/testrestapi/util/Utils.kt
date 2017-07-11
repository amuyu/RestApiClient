package com.amuyu.testrestapi.util

import android.net.Uri

/**
 * Created by noco on 2016-10-13.
 */
object Utils {

    fun getBaseUrl(url: String?): String? {
        return url?.substring(0, url.length - Uri.parse(url).path.length)
    }

    fun getPath(url: String): String {
        return Uri.parse(url).path
    }

}
