package com.lazycouple.restapiclient.ui;


import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amuyu.logger.Logger;
import com.lazycouple.restapiclient.databinding.RequestMainBinding;
import com.lazycouple.restapiclient.ui.adapter.ReqParamAdapter;
import com.lazycouple.restapiclient.ui.component.DaggerRestRequestComponent;
import com.lazycouple.restapiclient.ui.contract.RestRequestContract;
import com.lazycouple.restapiclient.ui.module.RestRequestModule;
import com.lazycouple.restapiclient.ui.presenter.RestRequestPresenter;
import com.lazycouple.restapiclient.ui.viewModel.RestRequestViewModel;

import javax.inject.Inject;

/**
 * Created by noco on 2016-10-12.
 */
public class RestRequestFragment extends Fragment implements
        RestRequestContract.View,
        LifecycleRegistryOwner {
    private final String TAG = RestRequestFragment.class.getSimpleName();

    @Inject
    RestRequestPresenter restRequestPresenter;

    RequestMainBinding binding;
    ReqParamAdapter paramAdapter;
    String id = null;

    LifecycleRegistry mLifecycleRegistry = new LifecycleRegistry(this);

    public static Fragment newInstance() {
        Fragment fragment = new RestRequestFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RestRequestViewModel viewModel = ViewModelProviders.of(this).get(RestRequestViewModel.class);
        DaggerRestRequestComponent.builder()
                .restRequestModule(new RestRequestModule(this, getActivity(), viewModel))
                .build().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Logger.d("");
        binding = RequestMainBinding.inflate(inflater, container, false);
        binding.setView(this);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Logger.d("");
        binding.setViewModel(restRequestPresenter.getViewModel());
        paramAdapter = new ReqParamAdapter(getActivity(), restRequestPresenter.getViewModel());
        binding.rvParameters.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.rvParameters.setAdapter(paramAdapter);
        restRequestPresenter.init(id);

    }



    public boolean onBackPressed()
    {
        return restRequestPresenter.onBackPressed();
    }

    @Override
    public void sendRequest() {
        Logger.d("");
        restRequestPresenter.requestRestApi(binding.etInputUrl.getText().toString(), paramAdapter.getItems());
    }

    @Override
    public void changeMethod() {
        Logger.d("");
        String method = binding.tvInputMethod.getText().toString();
        if(method.equals(RestRequestPresenter.Method.GET.name()))
            restRequestPresenter.setMethod(RestRequestPresenter.Method.POST);
        else if(method.equals(RestRequestPresenter.Method.POST.name()))
            restRequestPresenter.setMethod(RestRequestPresenter.Method.GET);
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void refresh() {
        Logger.d("");
        paramAdapter.notifyDataSetChanged();
    }



    @Override
    public void showError() {

    }


    @Override
    public LifecycleRegistry getLifecycle() {
        return mLifecycleRegistry;
    }
}
