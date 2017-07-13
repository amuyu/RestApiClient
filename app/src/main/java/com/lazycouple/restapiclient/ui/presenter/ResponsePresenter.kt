package com.lazycouple.restapiclient.ui.presenter

import android.content.Context
import android.os.Bundle
import com.amuyu.logger.Logger
import com.lazycouple.restapiclient.R
import com.lazycouple.restapiclient.ui.RestResponseFragment
import com.lazycouple.restapiclient.ui.contract.ResponseContract
import com.lazycouple.restapiclient.ui.data.CustomResponse
import com.lazycouple.restapiclient.ui.viewModel.ResponseViewModel
import javax.inject.Inject


/**
 * Created by noco on 2016-10-12.
 */
class ResponsePresenter @Inject
constructor(private val context: Context, private val view: ResponseContract.View, val viewModel: ResponseViewModel) : ResponseContract.Presenter {


    override fun init(bundle: Bundle) {
        Logger.d("" + context.getString(R.string.action_remove))
        if (bundle != null) viewModel.response = bundle.getParcelable<CustomResponse>(RestResponseFragment.KEY)
    }


    override fun start(context: Context) {

    }


}
