package com.lazycouple.restapiclient.ui.viewModel

import android.databinding.Bindable

import com.android.databinding.library.baseAdapters.BR
import com.lazycouple.restapiclient.repository.local.model.Parameter
import com.lazycouple.restapiclient.ui.data.CustomResponse
import com.lazycouple.restapiclient.ui.presenter.RestRequestPresenter.Method

/**
 * Created by amuyu on 2017. 6. 12..
 */

class RestRequestViewModel : AdapterViewModel<Parameter>() {

    internal var url: String? = null
    internal var method = Method.GET
    internal var isRequest: Boolean = false

    // response
    internal var response: CustomResponse? = null
    internal var code: String? = null
    internal var body: String? = null


    @Bindable
    fun getUrl(): String? {
        return url
    }

    fun setUrl(url: String) {
        this.url = url
        notifyPropertyChanged(BR.url)
    }

    fun getMethod(): Method {
        return method
    }

    fun setMethod(method: Method) {
        this.method = method
        notifyPropertyChanged(BR.methodText)
    }

    fun getResponse(): CustomResponse? {
        return response
    }

    fun setResponse(response: CustomResponse) {
        this.response = response
        notifyPropertyChanged(BR.code)
        notifyPropertyChanged(BR.body)
    }

    val methodText: String
        @Bindable
        get() = method.name

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

    @Bindable
    fun isRequest(): Boolean {
        return isRequest
    }

    fun setRequest(request: Boolean) {
        isRequest = request
        notifyPropertyChanged(BR.request)
    }
}

