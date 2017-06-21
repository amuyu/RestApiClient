package com.lazycouple.restapiclient.ui;


import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amuyu.logger.Logger;
import com.lazycouple.restapiclient.databinding.ResponseFragmentBinding;
import com.lazycouple.restapiclient.ui.component.DaggerResponseComponent;
import com.lazycouple.restapiclient.ui.contract.ResponseContract;
import com.lazycouple.restapiclient.ui.module.ContextModule;
import com.lazycouple.restapiclient.ui.module.ResponseModule;
import com.lazycouple.restapiclient.ui.presenter.ResponsePresenter;
import com.lazycouple.restapiclient.ui.viewModel.ResponseViewModel;

import javax.inject.Inject;

/**
 * Created by noco on 2016-10-12.
 */
public class RestResponseFragment extends Fragment implements
        ResponseContract.View,
        LifecycleRegistryOwner {
    public static String KEY = "RESPONSE";

    LifecycleRegistry mLifecycleRegistry = new LifecycleRegistry(this);

    @Inject
    ResponsePresenter presenter;
    ResponseFragmentBinding binding;



    public static Fragment newInstance() {
        Fragment fragment = new RestResponseFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ResponseViewModel viewModel = ViewModelProviders.of(this).get(ResponseViewModel.class);

        DaggerResponseComponent.builder()
                .responseModule(new ResponseModule(this, viewModel))
                .contextModule(new ContextModule(getActivity()))
                .build().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Logger.d("");
        binding = ResponseFragmentBinding.inflate(inflater, container, false);
        binding.setViewModel(presenter.getViewModel());
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Logger.d("");
        presenter.init(getArguments());
    }

    public boolean onBackPressed()
    {
        return false;
    }



    @Override
    public LifecycleRegistry getLifecycle() {
        return mLifecycleRegistry;
    }


}
