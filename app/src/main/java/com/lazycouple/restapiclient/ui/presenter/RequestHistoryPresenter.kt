package com.lazycouple.restapiclient.ui.presenter

import android.content.Context
import com.amuyu.logger.Logger
import com.lazycouple.restapiclient.data.RestRepository
import com.lazycouple.restapiclient.ui.contract.RequestHistoryContract
import com.lazycouple.restapiclient.ui.viewModel.RequestHistoryViewModel
import io.realm.Realm
import javax.inject.Inject

/**
 * Created by noco on 2016-10-27.
 */
class RequestHistoryPresenter @Inject
constructor(var context: Context, private val view: RequestHistoryContract.View,
            val viewModel: RequestHistoryViewModel, private val repository: RestRepository) : RequestHistoryContract.Presenter {
    private val realm: Realm

    init {
        this.realm = Realm.getDefaultInstance()
    }


    override fun init() {
        if (!viewModel.isInit) loadList()
    }

    override fun loadList() {
        viewModel.isInit = true
        //        List<String> histories = ConfigProperties.getHistories(context);

        repository.getApiHistories(realm).subscribe { results ->
            viewModel.lists = ArrayList(results)
            view.showList()
        }

    }

    override fun onClickedItem(position: Int) {
        Logger.d("")
        view.showRestRequset(viewModel.getItem(position).id)
    }

    override fun clearItems() {
        repository.clearApiHistories()
    }

    override fun destroy() {
        realm.close()
    }

    override fun start(context: Context) {
        // nothing
    }
}
