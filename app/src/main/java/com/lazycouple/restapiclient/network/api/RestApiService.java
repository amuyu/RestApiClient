package com.lazycouple.restapiclient.network.api;

import com.lazycouple.restapiclient.network.api.response.RepositoryResponse;
import com.lazycouple.restapiclient.network.api.response.UserResponse;

import java.util.List;
import java.util.Map;


import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by noco on 2016-10-12.
 */
public interface RestApiService {

    @GET("/users/{username}")
    Observable<UserResponse> getUser(
            @Path("username") String username
    );

    @GET("/users/{username}")
    Observable<Response<ResponseBody>> getUserResponse(
            @Path("username") String username
    );

    @GET("/users/{username}/repos")
    Observable<List<RepositoryResponse>> getUsersRepositories(
            @Path("username") String username
    );

    @GET
    Observable<Response<ResponseBody>> callApi(
            @Url String path, @QueryMap Map<String,String> options
    );

    @POST
    Observable<Response<ResponseBody>> callApiPost(
            @Url String path, @Body RequestBody body
    );
}
