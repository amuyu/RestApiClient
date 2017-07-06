package com.amuyu.testrestapi.network;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by noco on 2016-10-12.
 */
public interface RestApiService {


    @GET
    Observable<Response<ResponseBody>> callApi(
            @Url String path, @QueryMap Map<String, String> options
    );

    @POST
    Observable<Response<ResponseBody>> callApiPost(
            @Url String path, @Body RequestBody body
    );
}
