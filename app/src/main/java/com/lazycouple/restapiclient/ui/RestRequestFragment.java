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

import com.amuyu.logger.Logger;
import com.lazycouple.restapiclient.R;
import com.lazycouple.restapiclient.ui.adapter.ReqParamAdapter;
import com.lazycouple.restapiclient.ui.component.DaggerRestRequestComponent;
import com.lazycouple.restapiclient.ui.contract.RestRequestContract;
import com.lazycouple.restapiclient.ui.data.CustomResponse;
import com.lazycouple.restapiclient.ui.data.Parameter;
import com.lazycouple.restapiclient.ui.module.RestRequestModule;
import com.lazycouple.restapiclient.ui.presenter.RestRequestPresenter;
import com.lazycouple.restapiclient.util.ConfigProperties;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    String historyName = null;

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
        Logger.d("");


        paramAdapter = new ReqParamAdapter(getActivity());
        rv_parameters.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_parameters.setAdapter(paramAdapter);


        restRequestPresenter.init();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Logger.d("");
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

    @OnClick({R.id.bt_send, R.id.tv_input_method})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.bt_send:
                sendRequest();
                break;
            case R.id.tv_input_method:
                changeMethod();
                break;
        }


    }

    private void sendRequest() {
        ll_requset.setVisibility(View.GONE);
        restRequestPresenter.requestRestApi(et_input_url.getText().toString(), paramAdapter.getItems());
    }

    private void changeMethod() {
        String method = tv_input_method.getText().toString();
        if(method.equals(RestRequestPresenter.Method.GET.name()))
            restRequestPresenter.setMethod(RestRequestPresenter.Method.POST);
        else if(method.equals(RestRequestPresenter.Method.POST.name()))
            restRequestPresenter.setMethod(RestRequestPresenter.Method.GET);
    }

    public void setHistoryName(String historyName) {
        this.historyName = historyName;
    }

    @Override
    public void initView() {
        // BasicConsts.SKP_URL
//        String url = "http://54.92.43.68:8080/safenumber/v2/svc/opt";
        String url = "http://54.92.43.68:8180/safenumber/v3/default/svc/token";

        if(historyName != null)
        {
            List<Parameter> params = ConfigProperties.getApiInfo(getActivity(), historyName);
            for(Parameter param : params)
            {
                if(!param.getKey().equals("url"))
                    addParam(param);
                else
                    url = param.getValue();
            }
        }
        else
        {
            addParam(new Parameter("cpn","01058557235"));
            addParam(new Parameter("user_token","0f7094d5-09e3-40ef-93da-b41d79015db6"));
            addParam(new Parameter("device_type","2"));
            addParam(new Parameter("push_token","dr6qEYf69HM:APA91bFuy8eg59Jdi7w23T1eeOZ36HOkDgbndm8OCf9ChI_yYPGwnxLjkHfx5sTStYVlAlYKi647NWyH7X9R-gPWbW_sDA3W63jvaMBkxnagkd6m9L-7CJtehPxnULNGCXujnoL6CiJz"));
        }


        Logger.d("initView#url:"+url);
//        tv_input_method.setText("POST");
        et_input_url.setText(url);
    }

    @Override
    public void addParam(Parameter param) {
        paramAdapter.add(param);
        paramAdapter.notifyDataSetChanged();
    }

    @Override
    public void showResponse(CustomResponse response) {
        tv_response_code.setText(String.valueOf(response.code));

        String body = "";
        if(response.code == 200)
        {
            body = response.body;
        }
        else
        {
            body = response.errorbody;
        }

        tv_response_body.setText(body);

    }

    @Override
    public void showError() {

    }

    @Override
    public void setMethod(String method) {
        tv_input_method.setText(method);
    }
}
