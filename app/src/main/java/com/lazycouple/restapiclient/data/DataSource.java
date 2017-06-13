package com.lazycouple.restapiclient.data;

import java.util.List;

import rx.Observable;

/**
 * Created by amuyu on 2017. 6. 13..
 */

public interface DataSource {


    interface LoadHistoriesCallback {
        void onHistoriesCallback(List<String> histories);
    }

    Observable<List<String>> getHistories();
}
