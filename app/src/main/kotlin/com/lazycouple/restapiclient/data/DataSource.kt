package com.lazycouple.restapiclient.data

import com.lazycouple.restapiclient.db.model.Api
import com.lazycouple.restapiclient.db.model.Parameter
import com.lazycouple.restapiclient.ui.presenter.RestRequestPresenter

import io.realm.Realm
import rx.Observable

/**
 * Created by amuyu on 2017. 6. 13..
 */

interface DataSource {


    interface LoadHistoriesCallback {
        fun onHistoriesCallback(histories: List<String>)
    }

    fun getHistories(): Observable<List<String>>?
    fun getApiHistories(realm: Realm): Observable<List<Api>>
    fun addApi(url: String, method: RestRequestPresenter.Method, parameters: List<Parameter>)
    fun getApi(realm: Realm, id: String): Observable<Api>
    fun clearApiHistories()


}
