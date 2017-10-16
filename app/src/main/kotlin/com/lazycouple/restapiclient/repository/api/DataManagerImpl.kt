package com.amuyu.testrestapi.network

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import rx.Observable

/**
 * Created by noco on 2016-10-27.
 */
class DataManagerImpl : DataManager {

    private val apiManager: ApiManager

    init {
        this.apiManager = ApiManager()
    }


    override fun callApi(url: String, parameter: Map<String, String>, headerMap: Map<String, String>): Observable<Response<ResponseBody>>? {
        return apiManager?.callApi(url, parameter, headerMap)
    }

    override fun callApiPost(url: String, body: RequestBody, headerMap: Map<String, String>): Observable<Response<ResponseBody>>? {
        return apiManager?.callApiPost(url, body, headerMap)
    }

    override fun loadList(): List<String>? {
        return null
    }
}
