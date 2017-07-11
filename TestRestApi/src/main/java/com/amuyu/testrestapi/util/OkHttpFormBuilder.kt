package com.amuyu.testrestapi.util


import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.internal.Util
import java.io.UnsupportedEncodingException
import java.net.URLEncoder


/**
 * Created by noco on 2016-09-01.
 */
class OkHttpFormBuilder {

    val CONTENT_TYPE = MediaType.parse("application/x-www-form-urlencoded;charset=utf-8")
    private val content = StringBuilder()

    fun add(name: String, value: String): OkHttpFormBuilder {
        if (this.content.length > 0) {
            this.content.append('&')
        }

        try {
            this.content.append(URLEncoder.encode(name, "UTF-8")).append('=').append(URLEncoder.encode(value, "UTF-8"))
            return this
        } catch (var4: UnsupportedEncodingException) {
            throw AssertionError(var4)
        }

    }

    fun getContent(): String {
        return this.content.toString()
    }

    fun build(): RequestBody {
        if (this.content.length == 0) {
            throw IllegalStateException("Form encoded body must have at least one part.")
        } else {
            val contentBytes = this.content.toString().toByteArray(Util.UTF_8)
            return RequestBody.create(CONTENT_TYPE, contentBytes)
        }
    }
}
