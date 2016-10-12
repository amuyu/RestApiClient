package com.lazycouple.restapiclient.network.api;

import com.lazycouple.restapiclient.ui.data.User;
import com.lazycouple.restapiclient.util.Logger;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by noco on 2016-10-12.
 */
public class ApiManager {
    private final String TAG = ApiManager.class.getSimpleName();
    private RestApiService restApiService;

    public ApiManager(RestApiService restApiService) {
        this.restApiService = restApiService;
    }

    public Observable<User> getUser(String username) {
        Logger.d(TAG, "ApiManager#getUser");
        return restApiService.getUser(username)
                .map(userResponse -> {
                    Logger.d(TAG, Thread.currentThread().getName() + "#ApiManager#map");
                    User user = new User();
                    user.login = userResponse.login;
                    user.id = userResponse.id;
                    user.url = userResponse.url;
                    user.email = userResponse.email;
                    return user;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
