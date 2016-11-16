package com.lazycouple.restapiclient.data;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Response;
import rx.Observable;

/**
 * Created by noco on 2016-10-27.
 */
public class DataManagerImpl implements DataManager {

    private final ApiManager apiManager;

    @Inject
    public DataManagerImpl(ApiManager apiManager) {
        this.apiManager = apiManager;
    }


    @Override
    public Observable<Response<ResponseBody>> callApi(String url, Map<String, String> map) {
        return apiManager.callApi(url,map);
    }

    @Override
    public Observable<Response<ResponseBody>> callApiPost(String url, RequestBody body) {
        return apiManager.callApiPost(url,body);
    }

    @Override
    public List<String> loadList() {
        return null;
    }
}
