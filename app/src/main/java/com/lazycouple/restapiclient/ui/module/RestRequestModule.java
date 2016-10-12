package com.lazycouple.restapiclient.ui.module;

import com.lazycouple.restapiclient.network.ApiModule;
import com.lazycouple.restapiclient.network.api.ApiManager;
import com.lazycouple.restapiclient.network.api.RestApiService;
import com.lazycouple.restapiclient.ui.contract.RestRequestContract;
import com.lazycouple.restapiclient.ui.presenter.RestRequestPresenter;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by noco on 2016-10-12.
 */
@Module(includes = ApiModule.class)
public class RestRequestModule {

    private final RestRequestContract.View view;

    public RestRequestModule(RestRequestContract.View view) {
        this.view = view;
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
    public RestRequestContract.View provideView() {
        return view;
    }

    @Provides
    public RestRequestContract.Presenter providePresenter(RestRequestPresenter restRequestPresenter) {
        return restRequestPresenter;
    }


}
