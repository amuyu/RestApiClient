package com.lazycouple.restapiclient.data.local

import android.content.Context
import com.lazycouple.restapiclient.data.DataSource
import com.lazycouple.restapiclient.db.model.Api
import com.lazycouple.restapiclient.db.model.Parameter
import com.lazycouple.restapiclient.ui.presenter.RestRequestPresenter
import io.realm.Realm
import rx.Observable
import java.io.File
import java.util.*

/**
 * Created by amuyu on 2017. 6. 13..
 */

class RestLocalDataSource(private val context: Context) : DataSource {


    override fun getHistories(): Observable<List<String>>? {
        Realm.getDefaultInstance().use { realm -> }
        return null
    }

    override fun getApiHistories(realm: Realm): Observable<List<Api>> {
        return realm.where(Api::class.java).findAll()
                .asObservable()
                .map<List<Api>> { apis -> apis.toList() }
    }

    override fun addApi(url: String, method: RestRequestPresenter.Method, parameters: List<Parameter>) {
        Realm.getDefaultInstance().use { realm ->
            realm.executeTransaction { newRealm ->

                newRealm.createObject(Api::class.java, UUID.randomUUID().toString().replace("-", "")).apply {
                    setParameters(parameters)
                    this.url = url
                    this.date = Date()
                    this.method = method.value
                }

//                newRealm.copyToRealmOrUpdate(restapi)
                storeRealm(newRealm)
            }
        }
    }

    override fun getApi(realm: Realm, id: String): Observable<Api> {
        return realm.where(Api::class.java).equalTo("id", id)
                .findAll().asObservable()
                .map<Api> { apis ->
                    if (apis.size > 0) {
                        apis.first()
                    } else {
                        null
                    }
                }
    }

    override fun clearApiHistories() {
        Realm.getDefaultInstance().use { realm ->
            realm.beginTransaction()
            val apis = realm.where(Api::class.java).findAll()
            if (apis.size > 0) apis.deleteAllFromRealm()

            val parameters = realm.where(Parameter::class.java).findAll()
            if (parameters.size > 0) parameters.deleteAllFromRealm()
            realm.commitTransaction()
        }
    }

    private fun storeRealm(realm: Realm) {
        val file = File(context.externalCacheDir, "default.realm")
        file.delete()
        realm.writeCopyTo(file)
    }

    companion object {
        var INSTANCE: DataSource? = null

        fun getInstance(context: Context): DataSource {
            if (INSTANCE == null)
                INSTANCE = RestLocalDataSource(context)

            return INSTANCE!!
        }
    }
}
