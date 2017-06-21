package com.lazycouple.restapiclient.ui.viewModel;

import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;
import com.lazycouple.restapiclient.ui.data.CustomResponse;
import com.lazycouple.restapiclient.util.ObservableViewModel;

/**
 * Created by amuyu on 2017. 6. 21..
 */

public class ResponseViewModel extends ObservableViewModel {

    // response
    CustomResponse response;
    String code;
    String body;

    public CustomResponse getResponse() {
        return response;
    }

    public void setResponse(CustomResponse response) {
        this.response = response;
        notifyPropertyChanged(BR.code);
        notifyPropertyChanged(BR.body);
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

}
