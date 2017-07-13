package com.lazycouple.restapiclient.ui.contract

import com.lazycouple.restapiclient.ui.presenter.BasePresenter

/**
 * Created by noco on 2016-10-27.
 */
interface RequestHistoryContract {

    interface View {
        fun showList()
        fun showRestRequset(id: String?)
    }

    interface Presenter : BasePresenter {
        fun init()
        fun loadList()
        fun onClickedItem(position: Int)
        fun clearItems()
        fun destroy()
    }

}
