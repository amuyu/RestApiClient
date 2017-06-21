package com.lazycouple.restapiclient.ui.module;

import com.lazycouple.restapiclient.ui.contract.ResponseContract;
import com.lazycouple.restapiclient.ui.viewModel.ResponseViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by amuyu on 2017. 6. 21..
 */
@Module(includes = ContextModule.class)
public class ResponseModule {

    private final ResponseContract.View view;
    private final ResponseViewModel viewModel;

    public ResponseModule(ResponseContract.View view, ResponseViewModel viewModel) {
        this.view = view;
        this.viewModel = viewModel;
    }

    @Provides
    public ResponseContract.View provideView() {
        return view;
    }

    @Provides
    public ResponseViewModel provideViewModel() {
        return viewModel;
    }

    @Provides ResponseContract.Presenter providePresenter(ResponseContract.Presenter presenter) {
        return presenter;
    }
}
