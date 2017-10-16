package com.lazycouple.restapiclient.repository


import com.amuyu.testrestapi.network.ApiManager
import com.amuyu.testrestapi.network.DataManager
import com.lazycouple.restapiclient.repository.local.model.Api
import com.lazycouple.restapiclient.repository.local.model.Parameter
import com.lazycouple.restapiclient.ui.presenter.RestRequestPresenter
import io.realm.Realm
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import rx.Observable

/**
 * Created by amuyu on 2017. 6. 13..
 */

class RestRepository(private val localDataSource: DataSource,
                     private val remoteApi: DataManager) : DataSource, DataManager {

    private val apiManager: ApiManager

    init {
        this.apiManager = ApiManager()
    }

    /**
     *  RemoteApi
     */

    override fun callApi(url: String, parameter: Map<String, String>, headerMap: Map<String, String>): Observable<Response<ResponseBody>>? {
        return apiManager?.callApi(url, parameter, headerMap)
    }

    override fun callApiPost(url: String, body: RequestBody, headerMap: Map<String, String>): Observable<Response<ResponseBody>>? {
        return apiManager?.callApiPost(url, body, headerMap)
    }

    /**
     *  Local DB
     */

    override fun getHistories(): Observable<List<String>>? {
        return localDataSource.getHistories()
    }

    override fun getApiHistories(realm: Realm): Observable<List<Api>> {
        return localDataSource.getApiHistories(realm)
    }

    override fun addApi(url: String, method: RestRequestPresenter.Method, parameters: List<Parameter>) {
        localDataSource.addApi(url, method, parameters)
    }

    override fun getApi(realm: Realm, id: String): Observable<Api> {
        return localDataSource.getApi(realm, id)
    }

    override fun clearApiHistories() {
        localDataSource.clearApiHistories()
    }

    companion object {
        var INSTANCE: RestRepository? = null

        fun getInstance(localDataSource: DataSource, remoteApi: DataManager): RestRepository {
            if (INSTANCE == null) {
                INSTANCE = RestRepository(localDataSource, remoteApi)
            }
            return INSTANCE!!
        }
    }


}
