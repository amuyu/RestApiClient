package com.lazycouple.restapiclient.ui.viewModel

import android.databinding.Bindable

import com.android.databinding.library.baseAdapters.BR
import com.lazycouple.restapiclient.ui.data.CustomResponse
import com.lazycouple.restapiclient.util.ObservableViewModel

/**
 * Created by amuyu on 2017. 6. 21..
 */

class ResponseViewModel : ObservableViewModel() {

    // response
    internal var response: CustomResponse? = null
    internal var code: String? = null
    internal var body: String? = null

    fun getResponse(): CustomResponse? {
        return response
    }

    fun setResponse(response: CustomResponse) {
        this.response = response
        notifyPropertyChanged(BR.code)
        notifyPropertyChanged(BR.body)
    }

    @Bindable
    fun getCode(): String {
        if (response != null) return response!!.code.toString()
        return ""
    }

    @Bindable
    fun getBody(): String? {
        if (response != null) {
            if (response!!.code == 200)
                return response!!.body
            else
                return response!!.errorbody
        }
        return ""
    }

}
