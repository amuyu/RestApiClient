package com.lazycouple.restapiclient.ui.presenter;

import android.content.Context;
import android.util.Log;

import com.lazycouple.restapiclient.network.api.ApiManager;
import com.lazycouple.restapiclient.ui.contract.RestRequestContract;
import com.lazycouple.restapiclient.ui.data.Parameter;
import com.lazycouple.restapiclient.util.ConfigProperties;
import com.lazycouple.restapiclient.util.Utils;

import java.util.List;
import java.util.Map;

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


    }

    @Override
    public void requestRestApi(String url, List<Parameter> parameters) {

        Map<String, String> map = Utils.mapParameters(parameters);
        apiManager.callApi(url, map)
                .subscribe(
                        response -> {
                            view.showResponse(response);
                        },
                        Throwable::printStackTrace
                );
    }

    @Override
    public void loadList() {
//        apiManager.getUser("amuyu").subscribe(
//                user->{ Logger.d(TAG, "initView#user:" +user.toString());},
//                Throwable::printStackTrace
//        );

//        apiManager.getUsersRepositories("amuyu").subscribe(
//                repo->{ Logger.d(TAG, "initView#repo:" +repo.toString());},
//                Throwable::printStackTrace
//        );

        apiManager.getUserResponse("amuyu").subscribe(
            response-> {
                view.showResponse(response);
            },
            Throwable::printStackTrace
        );

    }

    @Override
    public void start(Context context) {

    }


}
