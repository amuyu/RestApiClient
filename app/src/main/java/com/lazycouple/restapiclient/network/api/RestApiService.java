package com.lazycouple.restapiclient.network.api;

import com.lazycouple.restapiclient.network.api.response.RepositoryResponse;
import com.lazycouple.restapiclient.network.api.response.UserResponse;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by noco on 2016-10-12.
 */
public interface RestApiService {

    @GET("/users/{username}")
    Observable<UserResponse> getUser(
            @Path("username") String username
    );

    @GET("/users/{username}/repos")
    Observable<List<RepositoryResponse>> getUsersRepositories(
            @Path("username") String username
    );
}
