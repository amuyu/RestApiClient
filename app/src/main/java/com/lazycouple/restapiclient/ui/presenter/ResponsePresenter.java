package com.lazycouple.restapiclient.ui.presenter;

import android.content.Context;
import android.os.Bundle;

import com.amuyu.logger.Logger;
import com.lazycouple.restapiclient.R;
import com.lazycouple.restapiclient.ui.contract.ResponseContract;
import com.lazycouple.restapiclient.ui.viewModel.ResponseViewModel;

import javax.inject.Inject;

import static com.lazycouple.restapiclient.ui.RestResponseFragment.KEY;


/**
 * Created by noco on 2016-10-12.
 */
public class ResponsePresenter implements ResponseContract.Presenter {

    private final Context context;
    private final ResponseContract.View view;
    private final ResponseViewModel viewModel;

    @Inject
    public ResponsePresenter(Context context, ResponseContract.View view, ResponseViewModel viewModel) {
        this.context = context;
        this.view = view;
        this.viewModel = viewModel;
    }


    @Override
    public void init(Bundle bundle) {
        Logger.d(""+context.getString(R.string.action_remove));
        if(bundle != null) viewModel.setResponse(bundle.getParcelable(KEY));
    }

    @Override
    public ResponseViewModel getViewModel() {
        return viewModel;
    }




    @Override
    public void start(Context context) {

    }


}
