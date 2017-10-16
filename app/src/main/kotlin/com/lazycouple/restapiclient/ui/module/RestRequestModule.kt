package com.lazycouple.restapiclient.ui.module

import android.content.Context

import com.lazycouple.restapiclient.Injection
import com.lazycouple.restapiclient.repository.RestRepository
import com.lazycouple.restapiclient.ui.contract.RestRequestContract
import com.lazycouple.restapiclient.ui.viewModel.RestRequestViewModel

import dagger.Module
import dagger.Provides

/**
 * Created by noco on 2016-10-12.
 */
@Module
class RestRequestModule(private val view: RestRequestContract.View,
                        private val context: Context,
                        private val viewModel: RestRequestViewModel) {

    @Provides
    fun provideView(): RestRequestContract.View {
        return view
    }

    @Provides
    fun provideContext(): Context {
        return context
    }

    @Provides
    fun provideViewModel(): RestRequestViewModel {
        return viewModel
    }

    @Provides
    fun providePresenter(restRequestPresenter: RestRequestContract.Presenter): RestRequestContract.Presenter {
        return restRequestPresenter
    }


}
