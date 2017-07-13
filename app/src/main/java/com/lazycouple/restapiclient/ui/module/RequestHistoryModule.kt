package com.lazycouple.restapiclient.ui.module

import android.content.Context
import com.lazycouple.restapiclient.Injection
import com.lazycouple.restapiclient.data.RestRepository
import com.lazycouple.restapiclient.ui.contract.RequestHistoryContract
import com.lazycouple.restapiclient.ui.viewModel.RequestHistoryViewModel
import dagger.Module
import dagger.Provides

/**
 * Created by noco on 2016-10-27.
 */
@Module
class RequestHistoryModule(private val context: Context, private val view: RequestHistoryContract.View, private val viewModel: RequestHistoryViewModel) {

    @Provides
    fun providePresenter(presenter: RequestHistoryContract.Presenter): RequestHistoryContract.Presenter {
        return presenter
    }

    @Provides
    fun provideView(): RequestHistoryContract.View {
        return view
    }

    @Provides
    fun provideContext(): Context {
        return context
    }

    @Provides
    fun provideViewModel(): RequestHistoryViewModel {
        return viewModel
    }

    @Provides
    fun provideRestRepository(): RestRepository {
        return Injection.provideRestRepository(context)
    }
}
