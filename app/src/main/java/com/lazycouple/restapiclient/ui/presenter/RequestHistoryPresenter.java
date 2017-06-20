package com.lazycouple.restapiclient.ui.presenter;

import android.content.Context;

import com.amuyu.logger.Logger;
import com.lazycouple.restapiclient.data.RestRepository;
import com.lazycouple.restapiclient.ui.contract.RequestHistoryContract;
import com.lazycouple.restapiclient.ui.viewModel.RequestHistoryViewModel;

import javax.inject.Inject;

import io.realm.Realm;

/**
 * Created by noco on 2016-10-27.
 */
public class RequestHistoryPresenter implements RequestHistoryContract.Presenter {
    private final Context context;
    private final RequestHistoryContract.View view;
    private final RequestHistoryViewModel viewModel;
    private final RestRepository repository;
    private Realm realm;

    @Inject
    public RequestHistoryPresenter(Context context, RequestHistoryContract.View view,
                                   RequestHistoryViewModel viewModel, RestRepository repository) {
        this.context = context;
        this.view = view;
        this.viewModel = viewModel;
        this.repository = repository;
        this.realm = Realm.getDefaultInstance();
    }


    @Override
    public void init() {
        if(!viewModel.isInit()) loadList();
    }

    @Override
    public void loadList() {
        viewModel.setInit(true);
//        List<String> histories = ConfigProperties.getHistories(context);

        repository.getApiHistories(realm).subscribe(results -> {
            viewModel.setLists(results);
            view.showList();
        });

    }

    @Override
    public RequestHistoryViewModel getViewModel() {
        return viewModel;
    }

    @Override
    public void onClickedItem(int position) {
        Logger.d("");
        view.showRestRequset(viewModel.getItem(position).getId());
    }

    @Override
    public void clearItems() {
        repository.clearApiHistories();
    }

    @Override
    public void destroy() {
        realm.close();
    }

    @Override
    public void start(Context context) {
        // nothing
    }
}
