package com.amuyu.testrestapi.network

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*
import rx.Observable

/**
 * Created by noco on 2016-10-12.
 */
interface RestApiService {


    @GET
    fun callApi(
            @Url path: String, @QueryMap options: Map<String, String>
    ): Observable<Response<ResponseBody>>

    @POST
    fun callApiPost(
            @Url path: String, @Body body: RequestBody
    ): Observable<Response<ResponseBody>>
}
