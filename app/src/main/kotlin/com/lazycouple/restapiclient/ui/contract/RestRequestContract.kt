package com.lazycouple.restapiclient.ui.contract

import com.lazycouple.restapiclient.db.model.Parameter
import com.lazycouple.restapiclient.ui.data.CustomResponse
import com.lazycouple.restapiclient.ui.presenter.BasePresenter
import com.lazycouple.restapiclient.ui.presenter.RestRequestPresenter

/**
 * Created by noco on 2016-10-12.
 */
interface RestRequestContract {

    interface View {
        fun refresh()
        fun showError()

        fun sendRequest()
        fun changeMethod()
        fun showResponse(response: CustomResponse?)
    }

    interface Presenter : BasePresenter {
        fun init(historyName: String?)
        fun loadData(historyName: String?)
        fun requestRestApi(url: String, parameters: List<Parameter>)

        var method: RestRequestPresenter.Method

        fun onBackPressed(): Boolean
        fun destroy()
    }
}
