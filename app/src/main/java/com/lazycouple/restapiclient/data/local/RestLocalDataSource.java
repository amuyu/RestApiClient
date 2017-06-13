package com.lazycouple.restapiclient.data.local;

import com.lazycouple.restapiclient.data.DataSource;

import java.util.List;

import rx.Observable;

/**
 * Created by amuyu on 2017. 6. 13..
 */

public class RestLocalDataSource implements DataSource {

    private static DataSource INSTANCE;

    public static DataSource getInstance() {
        if(INSTANCE == null)
            INSTANCE = new RestLocalDataSource();

        return INSTANCE;
    }

    @Override
    public Observable<List<String>> getHistories() {
        return null;
    }
}
