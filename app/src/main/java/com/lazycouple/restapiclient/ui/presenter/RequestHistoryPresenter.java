package com.lazycouple.restapiclient.ui.presenter;

import android.content.Context;

import com.amuyu.logger.Logger;
import com.lazycouple.restapiclient.data.RestRepository;
import com.lazycouple.restapiclient.db.model.Api;
import com.lazycouple.restapiclient.ui.contract.RequestHistoryContract;
import com.lazycouple.restapiclient.ui.viewModel.RequestHistoryViewModel;

import javax.inject.Inject;

/**
 * Created by noco on 2016-10-27.
 */
public class RequestHistoryPresenter implements RequestHistoryContract.Presenter {
    private final Context context;
    private final RequestHistoryContract.View view;
    private final RequestHistoryViewModel viewModel;
    private final RestRepository repository;

    @Inject
    public RequestHistoryPresenter(Context context, RequestHistoryContract.View view,
                                   RequestHistoryViewModel viewModel, RestRepository repository) {
        this.context = context;
        this.view = view;
        this.viewModel = viewModel;
        this.repository = repository;
    }


    @Override
    public void init() {
        if(!viewModel.isInit()) loadList();
    }

    @Override
    public void loadList() {
        viewModel.setInit(true);
//        List<String> histories = ConfigProperties.getHistories(context);

        repository.getApiHistories().subscribe(results -> {
            Logger.d(""+results.size());
            for(Api api : results)
                viewModel.addItem(api);
        });
        view.showList();
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
        Logger.d("");
        repository.clearApiHistories();

    }

    @Override
    public void start(Context context) {
        // nothing
    }
}
