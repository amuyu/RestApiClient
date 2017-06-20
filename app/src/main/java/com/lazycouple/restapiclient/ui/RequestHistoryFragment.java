package com.lazycouple.restapiclient.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.amuyu.logger.Logger;
import com.lazycouple.restapiclient.MainActivity;
import com.lazycouple.restapiclient.R;
import com.lazycouple.restapiclient.databinding.HistoryMainBinding;
import com.lazycouple.restapiclient.ui.adapter.ReqHistoryAdapter;
import com.lazycouple.restapiclient.ui.component.DaggerRequestHistoryComponent;
import com.lazycouple.restapiclient.ui.contract.RequestHistoryContract;
import com.lazycouple.restapiclient.ui.module.RequestHistoryModule;
import com.lazycouple.restapiclient.ui.presenter.RequestHistoryPresenter;
import com.lazycouple.restapiclient.ui.viewModel.RequestHistoryViewModel;

import javax.inject.Inject;

/**
 * Created by noco on 2016-10-13.
 */
public class RequestHistoryFragment extends Fragment implements RequestHistoryContract.View {
    private final String TAG = RequestHistoryFragment.class.getSimpleName();

    @Inject RequestHistoryPresenter requestHistoryPresenter;


    private HistoryMainBinding binding;
    private ReqHistoryAdapter historyAdapter;

    public static Fragment newInstance() {
        Fragment fragment = new RequestHistoryFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RequestHistoryViewModel viewModel = ViewModelProviders.of(this).get(RequestHistoryViewModel.class);
        DaggerRequestHistoryComponent.builder()
                .requestHistoryModule(new RequestHistoryModule(getActivity(), this, viewModel))
                .build().inject(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        historyAdapter = new ReqHistoryAdapter(getActivity(),
                requestHistoryPresenter.getViewModel(), requestHistoryPresenter);
        binding.rvHistoryList.setLayoutManager(new LinearLayoutManager(getActivity()));
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
        setHasOptionsMenu(true);
        binding = HistoryMainBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.history_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_remove) {
            Logger.d("");
            requestHistoryPresenter.clearItems();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showList() {
        historyAdapter.notifyDataSetChanged();
    }

    @Override
    public void showRestRequset(String id) {
        ((MainActivity)getActivity()).loadHistoryFragment(id);
    }

    @Override
    public void onDestroy() {
        requestHistoryPresenter.destroy();
        super.onDestroy();
    }
}
