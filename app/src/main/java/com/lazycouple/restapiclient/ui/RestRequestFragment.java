package com.lazycouple.restapiclient.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.lazycouple.restapiclient.R;
import com.lazycouple.restapiclient.ui.component.DaggerRestRequestComponent;
import com.lazycouple.restapiclient.ui.contract.RestRequestContract;
import com.lazycouple.restapiclient.ui.module.RestRequestModule;
import com.lazycouple.restapiclient.ui.presenter.RestRequestPresenter;
import com.lazycouple.restapiclient.util.Logger;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by noco on 2016-10-12.
 */
public class RestRequestFragment extends Fragment implements RestRequestContract.View {
    private final String TAG = RestRequestFragment.class.getSimpleName();

    @Inject
    RestRequestPresenter restRequestPresenter;

    @BindView(R.id.et_input_url)
    EditText et_input_url;

    @BindView(R.id.tv_input_method)
    TextView tv_input_method;

    public static Fragment newInstance() {
        Fragment fragment = new RestRequestFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerRestRequestComponent.builder()
                .restRequestModule(new RestRequestModule(this))
                .build().inject(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        restRequestPresenter.init();

        tv_input_method.setText("POST");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Logger.d(TAG, "onCreateView");
        View rootView = inflater.inflate(R.layout.request_main, container, false);
        ButterKnife.bind(this,rootView);
        return rootView;
    }

    @Override
    public void loadList() {

    }
}
