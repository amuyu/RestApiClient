package com.lazycouple.restapiclient.ui.module;

import android.content.Context;

import com.lazycouple.restapiclient.Injection;
import com.lazycouple.restapiclient.data.RestRepository;
import com.lazycouple.restapiclient.ui.contract.RestRequestContract;
import com.lazycouple.restapiclient.ui.viewModel.RestRequestViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by noco on 2016-10-12.
 */
@Module
public class RestRequestModule {

    private final RestRequestContract.View view;
    private final Context context;
    private final RestRequestViewModel viewModel;

    public RestRequestModule(RestRequestContract.View view, Context context, RestRequestViewModel viewModel) {
        this.view = view;
        this.context = context;
        this.viewModel = viewModel;
    }

    @Provides
    public RestRequestContract.View provideView() {
        return view;
    }

    @Provides
    public Context provideContext() {
        return context;
    }

    @Provides
    public RestRequestViewModel provideViewModel() {
        return viewModel;
    }

    @Provides
    public RestRequestContract.Presenter providePresenter(RestRequestContract.Presenter restRequestPresenter) {
        return restRequestPresenter;
    }

    @Provides
    public RestRepository provideRestRepository() {
        return Injection.provideRestRepository(context);
    }

}
