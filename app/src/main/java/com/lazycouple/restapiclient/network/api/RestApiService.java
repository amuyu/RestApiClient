package com.lazycouple.restapiclient.network.api;

import com.lazycouple.restapiclient.network.api.response.UserResponse;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by noco on 2016-10-12.
 */
public interface RestApiService {

    @GET("/usrs/{username}")
    Observable<UserResponse> getUser(
            @Path("username") String username
    );
}
