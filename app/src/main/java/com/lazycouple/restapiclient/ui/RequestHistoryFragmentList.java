package com.lazycouple.restapiclient.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amuyu.logger.Logger;
import com.lazycouple.restapiclient.MainActivity;
import com.lazycouple.restapiclient.databinding.HistoryMainListBinding;
import com.lazycouple.restapiclient.ui.adapter.ReqHistoryAdapter2;
import com.lazycouple.restapiclient.ui.component.DaggerRequestHistoryComponent2;
import com.lazycouple.restapiclient.ui.contract.RequestHistoryContract;
import com.lazycouple.restapiclient.ui.module.RequestHistoryModule;
import com.lazycouple.restapiclient.ui.presenter.RequestHistoryPresenter;
import com.lazycouple.restapiclient.ui.viewModel.RequestHistoryViewModel;

import javax.inject.Inject;

/**
 * Created by noco on 2016-10-13.
 */
public class RequestHistoryFragmentList extends Fragment implements RequestHistoryContract.View {
    private final String TAG = RequestHistoryFragmentList.class.getSimpleName();

    @Inject RequestHistoryPresenter requestHistoryPresenter;


    private HistoryMainListBinding binding;
    private ReqHistoryAdapter2 historyAdapter;

    public static Fragment newInstance() {
        Fragment fragment = new RequestHistoryFragmentList();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d("");
        RequestHistoryViewModel viewModel = ViewModelProviders.of(this).get(RequestHistoryViewModel.class);
        DaggerRequestHistoryComponent2.builder()
                .requestHistoryModule(new RequestHistoryModule(getActivity(), this, viewModel))
                .build().inject(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        historyAdapter = new ReqHistoryAdapter2(getActivity(),
                requestHistoryPresenter.getViewModel(), requestHistoryPresenter);
        binding.rvHistoryList.setAdapter(historyAdapter);

        binding.srHistoryRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // refresh event
                Logger.d("refresh event");
                requestHistoryPresenter.loadList();

            }
        });

        requestHistoryPresenter.init();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = HistoryMainListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void showList() {
        historyAdapter.notifyDataSetChanged();
    }

    @Override
    public void showRestRequset(String historyName) {
        ((MainActivity)getActivity()).loadHistoryFragment(historyName);
    }
}
