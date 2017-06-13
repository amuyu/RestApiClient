package com.lazycouple.restapiclient.data;


import com.amuyu.logger.Logger;

import java.util.List;

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
        Logger.d("");
        return localDataSource.getHistories();
    }
}
