package com.lazycouple.restapiclient.ui.module

import com.lazycouple.restapiclient.ui.contract.ResponseContract
import com.lazycouple.restapiclient.ui.viewModel.ResponseViewModel

import dagger.Module
import dagger.Provides

/**
 * Created by amuyu on 2017. 6. 21..
 */
@Module(includes = arrayOf(ContextModule::class))
class ResponseModule(private val view: ResponseContract.View,
                     private val viewModel: ResponseViewModel) {

    @Provides
    fun provideView(): ResponseContract.View {
        return view
    }

    @Provides
    fun provideViewModel(): ResponseViewModel {
        return viewModel
    }

    @Provides internal fun providePresenter(presenter: ResponseContract.Presenter): ResponseContract.Presenter {
        return presenter
    }
}
