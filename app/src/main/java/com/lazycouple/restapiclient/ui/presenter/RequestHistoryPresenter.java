package com.lazycouple.restapiclient.ui.presenter;

import android.content.Context;

import com.lazycouple.restapiclient.ui.contract.RequestHistoryContract;

import javax.inject.Inject;

/**
 * Created by noco on 2016-10-27.
 */
public class RequestHistoryPresenter implements RequestHistoryContract.Presenter {

    private final RequestHistoryContract.View view;

    @Inject
    public RequestHistoryPresenter(RequestHistoryContract.View view) {
        this.view = view;
    }


    @Override
    public void loadList() {
        view.showList();
    }

    @Override
    public void start(Context context) {
        view.initView();
    }
}
