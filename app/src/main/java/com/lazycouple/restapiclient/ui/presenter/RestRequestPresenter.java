package com.lazycouple.restapiclient.ui.presenter;

import android.content.Context;

import com.amuyu.logger.Logger;
import com.lazycouple.restapiclient.data.DataManager;
import com.lazycouple.restapiclient.ui.contract.RestRequestContract;
import com.lazycouple.restapiclient.ui.data.CustomResponse;
import com.lazycouple.restapiclient.ui.data.Parameter;
import com.lazycouple.restapiclient.util.ConfigProperties;
import com.lazycouple.restapiclient.util.Utils;
import com.lazycouple.restapiclient.ui.viewModel.RestRequestViewModel;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Response;
import rx.Subscriber;


/**
 * Created by noco on 2016-10-12.
 */
public class RestRequestPresenter implements RestRequestContract.Presenter {

    private final Context context;
    private final RestRequestContract.View view;
    private final DataManager dataManager;
    private RestRequestViewModel viewModel;

    public enum Method {
        GET, POST;
    }



    @Inject
    public RestRequestPresenter(Context context, RestRequestContract.View view,
                                DataManager apiManager, RestRequestViewModel viewModel) {
        this.context = context;
        this.view = view;
        this.dataManager = apiManager;
        this.viewModel = viewModel;
    }

    @Override
    public void loadData(String historyName) {

        String url = "http://54.92.43.68:8180/safenumber/v3/default/svc/token";

        if(historyName != null)
        {
            List<Parameter> params = ConfigProperties.getApiInfo(context, historyName);
            for(Parameter param : params)
            {
                if(!param.getKey().equals("url"))
                    viewModel.addItem(param);
                else
                    url = param.getValue();
            }
        }
        else
        {
            viewModel.addItem(new Parameter("cpn","01058557235"));
            viewModel.addItem(new Parameter("user_token","0f7094d5-09e3-40ef-93da-b41d79015db6"));
            viewModel.addItem(new Parameter("device_type","2"));
            viewModel.addItem(new Parameter("push_token","dr6qEYf69HM:APA91bFuy8eg59Jdi7w23T1eeOZ36HOkDgbndm8OCf9ChI_yYPGwnxLjkHfx5sTStYVlAlYKi647NWyH7X9R-gPWbW_sDA3W63jvaMBkxnagkd6m9L-7CJtehPxnULNGCXujnoL6CiJz"));
        }


        Logger.d("refresh#url:"+url);
        Logger.d(viewModel.getItemCount());
        viewModel.setUrl(url);
        view.refresh();
    }



    @Override
    public void requestRestApi(String url, List<Parameter> parameters) {
        viewModel.setRequest(true);
        switch (viewModel.getMethod()) {
            case GET:
                Map<String, String> map = Utils.mapParameters(parameters);
                requestGet(url, map);
                break;
            case POST:
                RequestBody body = Utils.bodyParameters(parameters);
                requestPost(url, body);
                break;
        }


    }

    @Override
    public RestRequestViewModel getViewModel() {
        return viewModel;
    }

    @Override
    public Method getMethod() {
        return viewModel.getMethod();
    }

    @Override
    public void setMethod(Method method) {
        viewModel.setMethod(method);
    }

    @Override
    public boolean onBackPressed() {

        if(viewModel.isRequest()) {
            viewModel.setRequest(false);
            return false;
        }

        return true;
    }

    private void requestGet(String url, Map<String,String> map) {
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
                            Logger.d("body : " + response.body().toString());

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

                        viewModel.setResponse(customResponse);
                    }
                });
    }

    private void requestPost(String url, RequestBody body) {
        dataManager.callApiPost(url, body)
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
                            Logger.d("body : " + response.body().toString());

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


                        viewModel.setResponse(customResponse);
                    }
                });
    }


    @Override
    public void start(Context context) {

    }


}
