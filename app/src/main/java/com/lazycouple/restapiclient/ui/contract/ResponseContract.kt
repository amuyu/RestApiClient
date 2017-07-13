package com.lazycouple.restapiclient.ui.contract

import android.os.Bundle
import com.lazycouple.restapiclient.ui.presenter.BasePresenter

/**
 * Created by noco on 2016-10-12.
 */
interface ResponseContract {

    interface View

    interface Presenter : BasePresenter {
        fun init(bundle: Bundle)
    }
}
