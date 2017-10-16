package com.lazycouple.restapiclient.util

import android.net.Uri
import com.lazycouple.restapiclient.repository.local.model.Parameter
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONException
import org.json.JSONObject
import java.util.*

/**
 * Created by noco on 2016-10-13.
 */
object Utils {

    fun getBaseUrl(url: String): String {
        return url.substring(0, url.length - Uri.parse(url).path.length)
    }

    fun getPath(url: String): String {
        return Uri.parse(url).path
    }

    fun mapParameters(params: List<Parameter>): Map<String, String> {
        val map = HashMap<String, String>()
        for (param in params) {
            map.put(param.key!!, param.value!!)
        }

        return map
    }

    fun bodyParameters(params: List<Parameter>): RequestBody? {
        val JSON = MediaType.parse("application/json; charset=utf-8")
        val `object` = paramToJson(params)
        return if (`object` != null) RequestBody.create(JSON, `object`.toString()) else null
    }

    fun paramToJson(params: List<Parameter>): JSONObject? {
        val `object` = JSONObject()
        try {
            for (param in params) {
                val value = param.key
                try {
                    val sub = JSONObject(param.value)
                    `object`.put(param.key, sub)
                } catch (e: JSONException) {
                    `object`.put(param.key, param.value)
                }

            }
            return `object`
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return null
    }
}
