package com.lazycouple.restapiclient.ui.contract;

import com.lazycouple.restapiclient.ui.presenter.BasePresenter;

/**
 * Created by noco on 2016-10-12.
 */
public interface RestRequestContract {

    interface View {
        void loadList();
    }

    interface Presenter extends BasePresenter {
        void init();
    }
}
