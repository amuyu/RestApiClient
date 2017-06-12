package com.lazycouple.restapiclient.ui.module;

import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.lazycouple.restapiclient.data.DataManager;
import com.lazycouple.restapiclient.data.DataManagerImpl;
import com.lazycouple.restapiclient.network.ApiModule;
import com.lazycouple.restapiclient.data.ApiManager;
import com.lazycouple.restapiclient.network.api.RestApiService;
import com.lazycouple.restapiclient.ui.contract.RestRequestContract;
import com.lazycouple.restapiclient.ui.presenter.RestRequestPresenter;
import com.lazycouple.restapiclient.ui.viewModel.RestRequestViewModel;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by noco on 2016-10-12.
 */
@Module(includes = ApiModule.class)
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
    public RestApiService provideRestApiService(Retrofit retrofit) {
        return retrofit.create(RestApiService.class);
    }

    @Provides
    public ApiManager provideApiManager(RestApiService restApiService) {
        return new ApiManager(restApiService);
    }

    @Provides
    public DataManager provideDataManager(ApiManager manager) {
        return new DataManagerImpl(manager);
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
    public RestRequestContract.Presenter providePresenter(RestRequestPresenter restRequestPresenter) {
        return restRequestPresenter;
    }


}
