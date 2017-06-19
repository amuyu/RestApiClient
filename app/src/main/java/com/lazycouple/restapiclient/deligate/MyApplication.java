package com.lazycouple.restapiclient.deligate;

import android.app.Application;

import com.amuyu.logger.DefaultLogPrinter;
import com.amuyu.logger.Logger;
import com.lazycouple.restapiclient.Injection;

import io.realm.Realm;
import io.realm.RealmConfiguration;


/**
 * Created by noco on 2016-10-12.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.addLogPrinter(new DefaultLogPrinter(this));
        initRealm();
    }

    public void initRealm() {
        Realm.init(this);
        RealmConfiguration realmConfig = Injection.provideRealmConfiguration(this);
        Realm.setDefaultConfiguration(realmConfig);
        Realm realm;
        try {
            realm = Realm.getDefaultInstance();
        } catch (IllegalArgumentException iae) {
            Logger.w(iae);
            Realm.deleteRealm(realmConfig);
            realm = Realm.getDefaultInstance();
        }
        realm.close();
    }
}
