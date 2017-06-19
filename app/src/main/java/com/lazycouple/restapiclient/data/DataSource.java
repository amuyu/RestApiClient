package com.lazycouple.restapiclient.data;

import com.lazycouple.restapiclient.db.model.Api;
import com.lazycouple.restapiclient.db.model.Parameter;

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
    Observable<List<Api>> getApiHistories();
    void addApi(String url, List<Parameter> parameters);
    Observable<Api> getApi(String id);


}
