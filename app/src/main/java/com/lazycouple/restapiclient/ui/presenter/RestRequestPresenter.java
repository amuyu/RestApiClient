package com.lazycouple.restapiclient.ui.presenter;

import android.content.Context;

import com.amuyu.logger.Logger;
import com.lazycouple.restapiclient.data.DataManager;
import com.lazycouple.restapiclient.data.RestRepository;
import com.lazycouple.restapiclient.db.model.Parameter;
import com.lazycouple.restapiclient.ui.contract.RestRequestContract;
import com.lazycouple.restapiclient.ui.data.CustomResponse;
import com.lazycouple.restapiclient.ui.viewModel.RestRequestViewModel;
import com.lazycouple.restapiclient.util.ConfigProperties;
import com.lazycouple.restapiclient.util.Utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.realm.Realm;
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
    private final RestRepository repository;
    private RestRequestViewModel viewModel;
    private Realm realm;

    public enum Method {
        GET(0), POST(1);

        public int value;
        private Method(int value) {
            this.value = value;
        }

        public static Method newInstance(int value) {
            if(value == GET.getValue()) return GET;
            else if(value == POST.getValue()) return POST;
            else return GET;
        }

        public int getValue() {
            return value;
        }
    }



    @Inject
    public RestRequestPresenter(Context context, RestRequestContract.View view,
                                DataManager apiManager, RestRequestViewModel viewModel, RestRepository repository) {
        this.context = context;
        this.view = view;
        this.dataManager = apiManager;
        this.viewModel = viewModel;
        this.repository = repository;
        realm = Realm.getDefaultInstance();
    }

    @Override
    public void init(String historyName) {
        if(viewModel.getUrl() == null) loadData(historyName);
    }

    @Override
    public void loadData(String id) {
        Logger.d("historyName:"+id);
        if(id != null)
        {
            repository.getApi(realm, id).subscribe(api -> {
                if(api != null) {
                    Logger.d(""+api.toString());
                    String url = api.getUrl();
                    List<Parameter> params = api.getParameters();
                    for(Parameter param : params)
                    {
                        if(!param.getKey().equals("url"))
                            viewModel.addItem(param);
                        else
                            url = param.getValue();
                    }
                    viewModel.setMethod(Method.newInstance(api.getMethod()));
                    showData(url);
                }
            });
        }
        else
        {
            Logger.d("history is null");
            String url = null;
            List<Parameter> params = ConfigProperties.getApiInfo(context, "fcmTest");
            for(Parameter param : params)
            {
                if(!param.getKey().equals("url"))
                    viewModel.addItem(param);
                else
                    url = param.getValue();
            }
            viewModel.setMethod(Method.POST);
            showData(url);
        }

    }

    private void showData(String url) {
        Logger.d("refresh#url:"+url);
        Logger.d(viewModel.getItemCount());
        viewModel.setUrl(url);
        view.refresh();
    }




    @Override
    public void requestRestApi(String url, List<Parameter> parameters ) {
        HashMap<String, String> temp = new HashMap<>();
        temp.put("Authorization", "Key=AIzaSyDuiS6N5E3lksUgwwIvd8_9Vj3PjJIcET8");

        repository.addApi(url, viewModel.getMethod(), parameters);
        viewModel.setRequest(true);
        switch (viewModel.getMethod()) {
            case GET:
                Map<String, String> map = Utils.mapParameters(parameters);
                requestGet(url, map, temp);
                break;
            case POST:
                RequestBody body = Utils.bodyParameters(parameters);
                requestPost(url, body, temp);
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

    @Override
    public void destroy() {
        realm.close();
    }

    private void requestGet(String url, Map<String,String> map, Map<String,String> headerMap) {
        dataManager.callApi(url, map, headerMap)
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

//                        viewModel.setResponse(customResponse);
                        view.showResponse(customResponse);
                    }
                });
    }

    private void requestPost(String url, RequestBody body, Map<String,String> headerMap) {
        dataManager.callApiPost(url, body, headerMap)
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


//                        viewModel.setResponse(customResponse);
                        view.showResponse(customResponse);
                    }
                });
    }


    @Override
    public void start(Context context) {

    }


}
