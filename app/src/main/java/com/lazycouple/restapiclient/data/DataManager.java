package com.lazycouple.restapiclient.data;

import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Response;
import rx.Observable;

/**
 * Created by noco on 2016-10-27.
 */
public interface DataManager {

    public Observable<Response<ResponseBody>> callApi(String url, Map<String,String> parameter, Map<String,String> headerMap);
    public Observable<Response<ResponseBody>> callApiPost(String url, RequestBody body, Map<String,String> headerMap);
    public List<String> loadList();
}
