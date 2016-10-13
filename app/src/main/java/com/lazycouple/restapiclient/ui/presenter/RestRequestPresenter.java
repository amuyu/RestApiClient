package com.lazycouple.restapiclient.ui.presenter;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.lazycouple.restapiclient.network.api.ApiManager;
import com.lazycouple.restapiclient.ui.contract.RestRequestContract;
import com.lazycouple.restapiclient.util.Logger;

import javax.inject.Inject;

/**
 * Created by noco on 2016-10-12.
 */
public class RestRequestPresenter implements RestRequestContract.Presenter {

    private final String TAG = RestRequestPresenter.class.getSimpleName();

    private final RestRequestContract.View view;
    private final ApiManager apiManager;

    @Inject
    public RestRequestPresenter(RestRequestContract.View view, ApiManager apiManager) {
        this.view = view;
        this.apiManager = apiManager;
    }

    @Override
    public void init() {
        Log.d(TAG, "init");
        loadList();
    }

    @Override
    public void loadList() {
//        apiManager.getUser("amuyu").subscribe(
//                user->{ Logger.d(TAG, "loadList#user:" +user.toString());},
//                Throwable::printStackTrace
//        );

        apiManager.getUsersRepositories("amuyu").subscribe(
                repo->{ Logger.d(TAG, "loadList#repo:" +repo.toString());},
                Throwable::printStackTrace
        );

    }

    @Override
    public void start(Context context) {

    }


}
