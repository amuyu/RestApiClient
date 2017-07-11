package com.amuyu.testrestapi.network


import com.amuyu.testrestapi.util.Utils
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.io.IOException

/**
 * Created by noco on 2016-10-12.
 */
class ApiManager {
    private val TAG = ApiManager::class.java.simpleName


    fun callApi(url: String, parameter: Map<String, String>, headerMap: Map<String, String>?): Observable<Response<ResponseBody>>? {
        var interceptor: Interceptor? = null
        if (headerMap != null) interceptor = HeaderInterceptors(headerMap)
        val restApiService = getRetrofit(Utils.getBaseUrl(url), interceptor)
                ?.create<RestApiService>(RestApiService::class.java) ?: return null
        return restApiService.callApi(Utils.getPath(url), parameter)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun callApiPost(url: String, body: RequestBody, headerMap: Map<String, String>?): Observable<Response<ResponseBody>>? {
        var interceptor: Interceptor? = null
        if (headerMap != null) interceptor = HeaderInterceptors(headerMap)
        val restApiService = getRetrofit(Utils.getBaseUrl(url), interceptor)
                ?.create<RestApiService>(RestApiService::class.java) ?: return null
        return restApiService.callApiPost(Utils.getPath(url), body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

    }

    private fun getRetrofit(baseUrl: String?, interceptor: Interceptor? = null): Retrofit? {
        if(baseUrl == null) return null;
        val httpClient = OkHttpClient.Builder()
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        httpClient.addInterceptor(loggingInterceptor)
        if (interceptor != null) httpClient.addInterceptor(interceptor)

        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(httpClient.build())
                .build()
    }

    inner class HeaderInterceptors(internal var map: Map<String, String>) : Interceptor {

        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
            val builder = chain.request().newBuilder()
            val keys = map.keys.iterator()
            while (keys.hasNext()) {
                val key = keys.next()
                builder.addHeader(key, map[key])
            }

            return chain.proceed(builder.build())
        }
    }

}
