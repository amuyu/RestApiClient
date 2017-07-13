package com.lazycouple.restapiclient.deligate

import android.app.Application
import com.amuyu.logger.DefaultLogPrinter
import com.amuyu.logger.Logger
import com.lazycouple.restapiclient.Injection
import io.realm.Realm


/**
 * Created by noco on 2016-10-12.
 */
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Logger.addLogPrinter(DefaultLogPrinter(this))
        initRealm()
    }

    fun initRealm() {
        Realm.init(this)
        val realmConfig = Injection.provideRealmConfiguration(this)
        Realm.setDefaultConfiguration(realmConfig)
        var realm: Realm
        try {
            realm = Realm.getDefaultInstance()
        } catch (iae: IllegalArgumentException) {
            Logger.w(iae)
            Realm.deleteRealm(realmConfig)
            realm = Realm.getDefaultInstance()
        }

        realm.close()
    }
}
