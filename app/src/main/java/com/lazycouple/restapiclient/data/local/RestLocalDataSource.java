package com.lazycouple.restapiclient.data.local;

import android.content.Context;

import com.amuyu.logger.Logger;
import com.lazycouple.restapiclient.data.DataSource;
import com.lazycouple.restapiclient.db.model.Api;
import com.lazycouple.restapiclient.db.model.Parameter;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by amuyu on 2017. 6. 13..
 */

public class RestLocalDataSource implements DataSource {

    private final Context context;
    private static DataSource INSTANCE;

    public static DataSource getInstance(Context context) {
        if(INSTANCE == null)
            INSTANCE = new RestLocalDataSource(context);

        return INSTANCE;
    }

    public RestLocalDataSource(Context context) {
        this.context = context;
        Logger.d("path:"+context.getExternalCacheDir());
    }

    @Override
    public Observable<List<String>> getHistories() {
        try (Realm realm = Realm.getDefaultInstance()) {
            // nothing
        }
        return null;
    }

    @Override
    public Observable<List<Api>> getApiHistories(Realm realm) {
        return realm.where(Api.class).findAll()
                .asObservable()
                .map(apis -> apis);
    }

    @Override
    public void addApi(String url, List<Parameter> parameters) {
        try (Realm realm = Realm.getDefaultInstance()) {
            realm.executeTransaction(newRealm -> {
                final Api restapi = new Api();
                restapi.setId(UUID.randomUUID().toString().replace("-",""));
                restapi.setParameters(parameters);
                restapi.setUrl(url);
                restapi.setDate(new Date());
                newRealm.copyToRealmOrUpdate(restapi);
                storeRealm(newRealm);
            });
        }
    }

    @Override
    public Observable<Api> getApi(Realm realm, String id) {
        return realm.where(Api.class).equalTo("id", id)
                .findAll().asObservable()
                .map(apis -> {
                    if(apis.size()>0) return apis.first();
                    return null;
                });
    }

    @Override
    public void clearApiHistories() {
        try (Realm realm = Realm.getDefaultInstance()) {
            realm.beginTransaction();
            RealmResults<Api> apis = realm.where(Api.class).findAll();
            if(apis.size() > 0) apis.deleteAllFromRealm();

            RealmResults<Parameter> parameters = realm.where(Parameter.class).findAll();
            if(parameters.size() > 0) parameters.deleteAllFromRealm();
            realm.commitTransaction();
        }
    }

    private void storeRealm(Realm realm) {
        File file = new File(context.getExternalCacheDir(), "default.realm");
        file.delete();
        realm.writeCopyTo(file);
    }
}
