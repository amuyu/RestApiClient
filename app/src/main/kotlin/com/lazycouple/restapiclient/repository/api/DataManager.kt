package com.amuyu.testrestapi.network

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import rx.Observable

/**
 * Created by noco on 2016-10-27.
 */
interface DataManager {

    fun callApi(url: String, parameter: Map<String, String>, headerMap: Map<String, String>): Observable<Response<ResponseBody>>?
    fun callApiPost(url: String, body: RequestBody, headerMap: Map<String, String>): Observable<Response<ResponseBody>>?
    fun loadList(): List<String>?
}
