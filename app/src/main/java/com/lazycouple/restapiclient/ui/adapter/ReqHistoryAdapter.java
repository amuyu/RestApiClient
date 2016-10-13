package com.lazycouple.restapiclient.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lazycouple.restapiclient.R;
import com.lazycouple.restapiclient.ui.listener.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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

        View v = LayoutInflater.from(mContext).inflate(R.layout.req_history_row, parent, false);
        HistoryHolder historyHolder = new HistoryHolder(v);
        ButterKnife.bind(historyHolder, v);
        return historyHolder;
    }

    @Override
    public void onBindViewHolder(HistoryHolder holder, int position) {
        String str = getItem(position);
        holder.tv_req_history_row.setText(str);
        holder.ll_history.setOnClickListener(v-> {
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

    static class HistoryHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_req_history_row)
        TextView tv_req_history_row;
        
        @BindView(R.id.ll_history)
        LinearLayout ll_history;

        public HistoryHolder(View itemView) {
            super(itemView);
        }
    }
}
