package com.lazycouple.restapiclient.ui.presenter;

import android.content.Context;

import com.lazycouple.restapiclient.data.DataManager;
import com.lazycouple.restapiclient.ui.contract.RestRequestContract;
import com.lazycouple.restapiclient.ui.data.CustomResponse;
import com.lazycouple.restapiclient.ui.data.Parameter;
import com.lazycouple.restapiclient.util.Logger;
import com.lazycouple.restapiclient.util.Utils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Response;
import rx.Subscriber;


/**
 * Created by noco on 2016-10-12.
 */
public class RestRequestPresenter implements RestRequestContract.Presenter {

    private final String TAG = RestRequestPresenter.class.getSimpleName();

    private final RestRequestContract.View view;
    private final DataManager dataManager;


    @Inject
    public RestRequestPresenter(RestRequestContract.View view, DataManager apiManager) {
        this.view = view;
        this.dataManager = apiManager;
    }

    @Override
    public void init() {
        view.initView();
    }

    @Override
    public void requestRestApi(String url, List<Parameter> parameters) {

        Map<String, String> map = Utils.mapParameters(parameters);
        dataManager.callApi(url, map)
                .subscribe(new Subscriber<Response<ResponseBody>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError();
                    }

                    @Override
                    public void onNext(Response<ResponseBody> response) {



                        CustomResponse customResponse = null;
                        if(response.code() == 200)
                        {
                            Logger.d(TAG, "body : " + response.body().toString());

                            try {
                                customResponse = new CustomResponse.CustomResponseBuilder()
                                        .code(response.code())
                                        .body(response.body().string())
                                        .build();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        else
                        {
                            try {
                                customResponse = new CustomResponse.CustomResponseBuilder()
                                        .code(response.code())
                                        .errorbody(response.errorBody().string())
                                        .build();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }


                        view.showResponse(customResponse);
                    }
                });
    }


    @Override
    public void start(Context context) {

    }


}
