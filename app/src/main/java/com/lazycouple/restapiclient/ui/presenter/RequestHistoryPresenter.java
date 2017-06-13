package com.lazycouple.restapiclient.ui.presenter;

import android.content.Context;

import com.amuyu.logger.Logger;
import com.lazycouple.restapiclient.ui.contract.RequestHistoryContract;
import com.lazycouple.restapiclient.util.ConfigProperties;
import com.lazycouple.restapiclient.ui.viewModel.RequestHistoryViewModel;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by noco on 2016-10-27.
 */
public class RequestHistoryPresenter implements RequestHistoryContract.Presenter {
    private final Context context;
    private final RequestHistoryContract.View view;
    private final RequestHistoryViewModel viewModel;

    @Inject
    public RequestHistoryPresenter(Context context, RequestHistoryContract.View view, RequestHistoryViewModel viewModel) {
        this.context = context;
        this.view = view;
        this.viewModel = viewModel;
    }


    @Override
    public void init() {
        if(!viewModel.isInit()) loadList();
    }

    @Override
    public void loadList() {
        viewModel.setInit(true);
        List<String> histories = ConfigProperties.getHistories(context);
        for(String str:histories) viewModel.addItem(str);
        view.showList();
    }

    @Override
    public RequestHistoryViewModel getViewModel() {
        return viewModel;
    }

    @Override
    public void onClickedItem(int position) {
        Logger.d("");
        view.showRestRequset(viewModel.getItem(position));
    }

    @Override
    public void start(Context context) {
        // nothing
    }
}
