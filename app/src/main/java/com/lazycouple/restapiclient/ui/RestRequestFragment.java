package com.lazycouple.restapiclient.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lazycouple.restapiclient.R;
import com.lazycouple.restapiclient.consts.BasicConsts;
import com.lazycouple.restapiclient.ui.adapter.ReqParamAdapter;
import com.lazycouple.restapiclient.ui.component.DaggerRestRequestComponent;
import com.lazycouple.restapiclient.ui.contract.RestRequestContract;
import com.lazycouple.restapiclient.ui.data.Parameter;
import com.lazycouple.restapiclient.ui.module.RestRequestModule;
import com.lazycouple.restapiclient.ui.presenter.RestRequestPresenter;
import com.lazycouple.restapiclient.util.ConfigProperties;
import com.lazycouple.restapiclient.util.Logger;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by noco on 2016-10-12.
 */
public class RestRequestFragment extends Fragment implements RestRequestContract.View {
    private final String TAG = RestRequestFragment.class.getSimpleName();

    @Inject
    RestRequestPresenter restRequestPresenter;

    @BindView(R.id.et_input_url) EditText et_input_url;
    @BindView(R.id.tv_input_method) TextView tv_input_method;
    @BindView(R.id.tv_response_code) TextView tv_response_code;
    @BindView(R.id.tv_response_body) TextView tv_response_body;
    @BindView(R.id.rv_parameters) RecyclerView rv_parameters;
    @BindView(R.id.bt_send) Button bt_send;
    @BindView(R.id.ll_requset) LinearLayout ll_requset;


    ReqParamAdapter paramAdapter;

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



        paramAdapter = new ReqParamAdapter(getActivity());
        rv_parameters.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_parameters.setAdapter(paramAdapter);


        initView();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Logger.d(TAG, "onCreateView");
        View rootView = inflater.inflate(R.layout.request_main, container, false);
        ButterKnife.bind(this,rootView);
        return rootView;
    }

    public boolean onBackPressed()
    {
        if(ll_requset.getVisibility() == View.VISIBLE)
            return true;

        ll_requset.setVisibility(View.VISIBLE);
        return false;
    }

    @OnClick(R.id.bt_send)
    public void onSendClick() {

        ll_requset.setVisibility(View.GONE);
        restRequestPresenter.requestRestApi(et_input_url.getText().toString(), paramAdapter.getItems());
    }

    @Override
    public void initView() {
        // BasicConsts.SKP_URL
        String url = "http://";

        List<Parameter> params = ConfigProperties.getApiInfo(getActivity(), "versionCheck");
        for(Parameter param : params)
        {
            if(!param.getKey().equals("url"))
                addParam(param);
            else
                url = param.getValue();
        }

        tv_input_method.setText("POST");
        et_input_url.setText(url);
    }

    @Override
    public void addParam(Parameter param) {
        paramAdapter.add(param);
        paramAdapter.notifyDataSetChanged();
    }

    @Override
    public void showResponse(Response<ResponseBody> response) {
        tv_response_code.setText(String.valueOf(response.code()));

        String body = "";
        if(response.code() == 200)
        {
            try {
                body = response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {
            try {
                body = response.errorBody().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        tv_response_body.setText(body);

    }
}
