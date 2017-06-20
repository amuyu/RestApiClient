package com.lazycouple.restapiclient.data;


import com.lazycouple.restapiclient.db.model.Api;
import com.lazycouple.restapiclient.db.model.Parameter;

import java.util.List;

import io.realm.Realm;
import rx.Observable;

/**
 * Created by amuyu on 2017. 6. 13..
 */

public class RestRepository implements DataSource {

    private static RestRepository INSTANCE = null;
    private final DataSource localDataSource;

    public RestRepository(DataSource localDataSource) {
        this.localDataSource = localDataSource;
    }

    public static RestRepository getInstance(DataSource localDataSource) {
        if(INSTANCE == null) {
            INSTANCE = new RestRepository(localDataSource);
        }
        return INSTANCE;
    }

    @Override
    public Observable<List<String>> getHistories() {
        return localDataSource.getHistories();
    }

    @Override
    public Observable<List<Api>> getApiHistories(Realm realm) {
        return localDataSource.getApiHistories(realm);
    }

    @Override
    public void addApi(String url, List<Parameter> parameters) {
        localDataSource.addApi(url, parameters);
    }

    @Override
    public Observable<Api> getApi(Realm realm, String id) {
        return localDataSource.getApi(realm,id);
    }

    @Override
    public void clearApiHistories() {
        localDataSource.clearApiHistories();
    }


}
