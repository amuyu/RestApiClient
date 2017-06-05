package com.lazycouple.restapiclient.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amuyu.logger.Logger;
import com.lazycouple.restapiclient.MainActivity;
import com.lazycouple.restapiclient.R;
import com.lazycouple.restapiclient.ui.adapter.ReqHistoryAdapter;
import com.lazycouple.restapiclient.ui.component.DaggerRequestHistoryComponent;
import com.lazycouple.restapiclient.ui.contract.RequestHistoryContract;
import com.lazycouple.restapiclient.ui.module.RequestHistoryModule;
import com.lazycouple.restapiclient.ui.presenter.RequestHistoryPresenter;
import com.lazycouple.restapiclient.util.ConfigProperties;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by noco on 2016-10-13.
 */
public class RequestHistoryFragment extends Fragment implements RequestHistoryContract.View {
    private final String TAG = RequestHistoryFragment.class.getSimpleName();

    @Inject RequestHistoryPresenter requestHistoryPresenter;

    @BindView(R.id.sr_history_refresh) SwipeRefreshLayout sr_history_refresh;
    @BindView(R.id.rv_history_list) RecyclerView rv_history_list;

    private ReqHistoryAdapter historyAdapter;

    public static Fragment newInstance() {
        Fragment fragment = new RequestHistoryFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerRequestHistoryComponent.builder()
                .requestHistoryModule(new RequestHistoryModule(this))
                .build().inject(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        historyAdapter = new ReqHistoryAdapter(getActivity());
        historyAdapter.setRecyclerItemClickListener(((adapter, position) -> {
            String historyName = ((ReqHistoryAdapter)adapter).getItem(position);
            ((MainActivity)getActivity()).loadHistoryFragment(historyName);
        }));
        rv_history_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_history_list.setAdapter(historyAdapter);

        sr_history_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // refresh event
                Logger.d("refresh event");

            }
        });

        requestHistoryPresenter.start(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.history_main, container, false);
        ButterKnife.bind(this,rootView);
        return rootView;
    }



    private void addHistory(String history) {
        historyAdapter.add(history);
    }

    @Override
    public void initView() {
        List<String> histories = ConfigProperties.getHistories(getActivity());
        for(String str:histories) addHistory(str);
    }

    @Override
    public void showList() {
        Logger.d("showList");
    }
}
