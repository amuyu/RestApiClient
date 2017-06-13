package com.lazycouple.restapiclient.ui.module;

import android.content.Context;

import com.lazycouple.restapiclient.ui.contract.RequestHistoryContract;
import com.lazycouple.restapiclient.ui.viewModel.RequestHistoryViewModel;
import com.lazycouple.restapiclient.data.RestRepository;
import com.lazycouple.restapiclient.Injection;

import dagger.Module;
import dagger.Provides;

/**
 * Created by noco on 2016-10-27.
 */
@Module
public class RequestHistoryModule {

    private final Context context;
    private final RequestHistoryContract.View view;
    private final RequestHistoryViewModel viewModel;

    public RequestHistoryModule(Context context, RequestHistoryContract.View view, RequestHistoryViewModel viewModel) {
        this.context = context;
        this.view = view;
        this.viewModel = viewModel;
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

    @Provides
    public Context provideContext() {
        return context;
    }

    @Provides
    public RequestHistoryViewModel provideViewModel() {
        return viewModel;
    }

    @Provides
    public RestRepository provideRestRepository() {
        return Injection.provideRestRepository();
    }
}
