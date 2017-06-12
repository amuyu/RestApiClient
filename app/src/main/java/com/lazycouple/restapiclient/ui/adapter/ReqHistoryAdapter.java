package com.lazycouple.restapiclient.ui.adapter;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.android.databinding.library.baseAdapters.BR;
import com.lazycouple.restapiclient.databinding.ReqHistoryRowBinding;
import com.lazycouple.restapiclient.ui.listener.RecyclerItemClickListener;
import com.lazycouple.restapiclient.util.BindingHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by noco on 2016-10-13.
 */
public class ReqHistoryAdapter extends RecyclerView.Adapter<ReqHistoryAdapter.HistoryHolder> {
    private final String TAG = ReqHistoryAdapter.class.getSimpleName();
    private Context mContext;
    private List<String> histories;
    private RecyclerItemClickListener recyclerItemClickListener;

    public ReqHistoryAdapter(Context mContext) {
        this.mContext = mContext;
        histories = new ArrayList<>();
    }

    public void add(String str) {
        histories.add(str);
    }

    public String getItem(int position) {
        return histories.get(position);
    }

    public List<String> getItems() {
        return histories;
    }

    @Override
    public HistoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ReqHistoryRowBinding binding = ReqHistoryRowBinding.inflate(LayoutInflater.from(mContext), parent, false);
        return new HistoryHolder(binding);
    }

    @Override
    public void onBindViewHolder(HistoryHolder holder, int position) {
        String str = getItem(position);
        holder.bind(str);
        ReqHistoryRowBinding binding = (ReqHistoryRowBinding)holder.getBinding();
        binding.llHistory.setOnClickListener(v->{
            if(recyclerItemClickListener != null)
                recyclerItemClickListener.onItemClick(ReqHistoryAdapter.this, position);
        });
    }

    public void setRecyclerItemClickListener(RecyclerItemClickListener recyclerItemClickListener) {
        this.recyclerItemClickListener = recyclerItemClickListener;
    }

    @Override
    public int getItemCount() {
        return histories.size();
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
