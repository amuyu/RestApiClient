package com.lazycouple.restapiclient.ui.contract;

import com.lazycouple.restapiclient.ui.presenter.BasePresenter;

/**
 * Created by noco on 2016-10-27.
 */
public interface RequestHistoryContract {

    interface View {
        void initView();
        void showList();
    }

    interface Presenter extends BasePresenter {
        void loadList();
    }

}
