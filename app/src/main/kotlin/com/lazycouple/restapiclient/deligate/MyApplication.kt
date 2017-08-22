package com.lazycouple.restapiclient.deligate

import android.app.Application
import com.amuyu.logger.DefaultLogPrinter
import com.amuyu.logger.Logger
import com.facebook.stetho.Stetho
import com.lazycouple.restapiclient.Injection
import com.lazycouple.restapiclient.ui.ApplicationComponent
import com.lazycouple.restapiclient.ui.ApplicationModule
import com.lazycouple.restapiclient.ui.DaggerApplicationComponent
import io.realm.Realm


/**
 * Created by noco on 2016-10-12.
 */
class MyApplication : Application() {

    lateinit var appComponent: ApplicationComponent
        private set

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
        app = this

        Logger.addLogPrinter(DefaultLogPrinter(this))
        initRealm()
        Stetho.initializeWithDefaults(this)
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

    companion object {
        lateinit var app: MyApplication
            private set
    }
}
