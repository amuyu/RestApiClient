package com.lazycouple.restapiclient.repository


import com.lazycouple.restapiclient.repository.local.model.Api
import com.lazycouple.restapiclient.repository.local.model.Parameter
import com.lazycouple.restapiclient.ui.presenter.RestRequestPresenter
import io.realm.Realm
import rx.Observable

/**
 * Created by amuyu on 2017. 6. 13..
 */

class RestRepository(private val localDataSource: DataSource) : DataSource {

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

        fun getInstance(localDataSource: DataSource): RestRepository {
            if (INSTANCE == null) {
                INSTANCE = RestRepository(localDataSource)
            }
            return INSTANCE!!
        }
    }


}
