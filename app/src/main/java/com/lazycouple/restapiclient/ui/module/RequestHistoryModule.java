package com.lazycouple.restapiclient.ui.module;

import com.lazycouple.restapiclient.ui.contract.RequestHistoryContract;
import com.lazycouple.restapiclient.ui.contract.RestRequestContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by noco on 2016-10-27.
 */
@Module
public class RequestHistoryModule {

    private final RequestHistoryContract.View view;

    public RequestHistoryModule(RequestHistoryContract.View view) {
        this.view = view;
    }

    @Provides
    public RequestHistoryContract.Presenter providePresenter(RequestHistoryContract.Presenter presenter)
    {
        return presenter;
    }

    @Provides
    public RequestHistoryContract.View provideView() {
        return view;
    }
}
