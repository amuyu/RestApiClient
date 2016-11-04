package com.lazycouple.restapiclient.ui.contract;

import com.lazycouple.restapiclient.ui.data.CustomResponse;
import com.lazycouple.restapiclient.ui.data.Parameter;
import com.lazycouple.restapiclient.ui.presenter.BasePresenter;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by noco on 2016-10-12.
 */
public interface RestRequestContract {

    interface View {
        void initView();
        void addParam(Parameter param);
        void showResponse(CustomResponse responseBody);
        void showError();
    }

    interface Presenter extends BasePresenter {
        void init();
        void requestRestApi(String url, List<Parameter> parameters);
    }
}
