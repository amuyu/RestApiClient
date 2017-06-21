package com.lazycouple.restapiclient.ui.contract;

import android.os.Bundle;

import com.lazycouple.restapiclient.ui.presenter.BasePresenter;
import com.lazycouple.restapiclient.ui.viewModel.ResponseViewModel;

/**
 * Created by noco on 2016-10-12.
 */
public interface ResponseContract {

    interface View {

    }

    interface Presenter extends BasePresenter {
        void init(Bundle bundle);
        ResponseViewModel getViewModel();
    }
}
