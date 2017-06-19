package com.lazycouple.restapiclient.ui.contract;

import com.lazycouple.restapiclient.db.model.Parameter;
import com.lazycouple.restapiclient.ui.presenter.BasePresenter;
import com.lazycouple.restapiclient.ui.presenter.RestRequestPresenter;
import com.lazycouple.restapiclient.ui.viewModel.RestRequestViewModel;

import java.util.List;

/**
 * Created by noco on 2016-10-12.
 */
public interface RestRequestContract {

    interface View {
        void refresh();
        void showError();

        void sendRequest();
        void changeMethod();
    }

    interface Presenter extends BasePresenter {
        void init(String historyName);
        void loadData(String historyName);
        void requestRestApi(String url, List<Parameter> parameters);
        RestRequestViewModel getViewModel();

        RestRequestPresenter.Method getMethod();
        void setMethod(RestRequestPresenter.Method method);

        boolean onBackPressed();
    }
}
