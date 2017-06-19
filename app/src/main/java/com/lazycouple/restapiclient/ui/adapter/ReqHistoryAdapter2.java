package com.lazycouple.restapiclient.ui.adapter;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.amuyu.logger.Logger;
import com.android.databinding.library.baseAdapters.BR;
import com.lazycouple.restapiclient.databinding.ReqHistoryRowBinding;
import com.lazycouple.restapiclient.ui.contract.RequestHistoryContract.Presenter;
import com.lazycouple.restapiclient.ui.viewModel.RequestHistoryViewModel;
import com.lazycouple.restapiclient.util.BindingHolder;

/**
 * Created by noco on 2016-10-13.
 */
public class ReqHistoryAdapter2 extends BaseAdapter {
    private final String TAG = ReqHistoryAdapter2.class.getSimpleName();
    private Context mContext;
    private RequestHistoryViewModel viewModel;
    private Presenter presenter;
    private LayoutInflater inflater;


    public ReqHistoryAdapter2(Context mContext,
                              RequestHistoryViewModel viewModel, Presenter presenter) {
        Logger.d("");
        inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mContext = mContext;
        this.viewModel = viewModel;
        this.presenter = presenter;
    }

    @Override
    public int getCount() {
        return viewModel.getItemCount();
    }

    public String getItem(int position) {
        return viewModel.getItem(position).getUrl();
    }

    @Override
    public long getItemId(int pos) {
        return viewModel.getItemId(pos);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        ReqHistoryRowBinding binding;
        if(view==null){
            binding = ReqHistoryRowBinding.inflate(LayoutInflater.from(mContext), parent, false);
//            binding.getRoot().setTag(binding);
        } else {
            binding = ReqHistoryRowBinding.bind(view);

            // tag 형태로 사용하려면 binding.getRoot().setTag() 같이 주석 풀어야함
//            binding = (ReqHistoryRowBinding)view.getTag();
        }

        String title = getItem(position);
        binding.setTitle(title);
        binding.setPosition(position);
        binding.setPresenter(presenter);

        return binding.getRoot();
    }


}
