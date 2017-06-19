package com.lazycouple.restapiclient.ui.viewModel;

import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;
import com.lazycouple.restapiclient.ui.data.CustomResponse;
import com.lazycouple.restapiclient.db.model.Parameter;
import com.lazycouple.restapiclient.ui.presenter.RestRequestPresenter.Method;

/**
 * Created by amuyu on 2017. 6. 12..
 */

public class RestRequestViewModel extends AdapterViewModel<Parameter> {

    String url;
    Method method = Method.GET;
    boolean isRequest;

    // response
    CustomResponse response;
    String code;
    String body;


    @Bindable
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
        notifyPropertyChanged(BR.url);
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
        notifyPropertyChanged(BR.methodText);
    }

    public CustomResponse getResponse() {
        return response;
    }

    public void setResponse(CustomResponse response) {
        this.response = response;
        notifyPropertyChanged(BR.code);
        notifyPropertyChanged(BR.body);
    }

    @Bindable
    public String getMethodText() {
        return method.name();
    }

    @Bindable
    public String getCode() {
        if(response != null) return String.valueOf(response.code);
        return "";
    }

    @Bindable
    public String getBody() {
        if(response != null) {
            if(response.code == 200) return response.body;
            else return response.errorbody;
        }
        return "";
    }

    @Bindable
    public boolean isRequest() {
        return isRequest;
    }

    public void setRequest(boolean request) {
        isRequest = request;
        notifyPropertyChanged(BR.request);
    }
}

