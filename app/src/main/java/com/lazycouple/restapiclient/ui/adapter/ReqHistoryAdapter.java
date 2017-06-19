package com.lazycouple.restapiclient.ui.adapter;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.android.databinding.library.baseAdapters.BR;
import com.lazycouple.restapiclient.databinding.ReqHistoryRowBinding;
import com.lazycouple.restapiclient.db.model.Api;
import com.lazycouple.restapiclient.ui.contract.RequestHistoryContract.Presenter;
import com.lazycouple.restapiclient.ui.viewModel.RequestHistoryViewModel;
import com.lazycouple.restapiclient.util.BindingHolder;

/**
 * Created by noco on 2016-10-13.
 */
public class ReqHistoryAdapter extends RecyclerView.Adapter<ReqHistoryAdapter.HistoryHolder> {
    private final String TAG = ReqHistoryAdapter.class.getSimpleName();
    private Context mContext;
    private RequestHistoryViewModel viewModel;
    private Presenter presenter;


    public ReqHistoryAdapter(Context mContext,
                             RequestHistoryViewModel viewModel, Presenter presenter) {
        this.mContext = mContext;
        this.viewModel = viewModel;
        this.presenter = presenter;
    }

    public Api getItem(int position) {
        return viewModel.getItem(position);
    }

    @Override
    public HistoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ReqHistoryRowBinding binding = ReqHistoryRowBinding.inflate(LayoutInflater.from(mContext), parent, false);
        return new HistoryHolder(binding);
    }

    @Override
    public void onBindViewHolder(HistoryHolder holder, int position) {
        String title = getItem(position).getUrl();
        ReqHistoryRowBinding binding = (ReqHistoryRowBinding)holder.getBinding();
        binding.setTitle(title);
        binding.setPosition(position);
        binding.setPresenter(presenter);
    }

    @Override
    public int getItemCount() {
        return viewModel.getItemCount();
    }

    public class HistoryHolder extends BindingHolder<String> {

        public HistoryHolder(ViewDataBinding binding) {
            super(binding);
        }


        @Override
        public void bind(String variable) {
            binding.setVariable(BR.title, variable);
        }
    }
}
