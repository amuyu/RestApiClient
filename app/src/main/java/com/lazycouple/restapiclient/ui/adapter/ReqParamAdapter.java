package com.lazycouple.restapiclient.ui.adapter;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.amuyu.logger.Logger;
import com.android.databinding.library.baseAdapters.BR;
import com.lazycouple.restapiclient.databinding.ReqParametersRowBinding;
import com.lazycouple.restapiclient.db.model.Parameter;
import com.lazycouple.restapiclient.ui.viewModel.RestRequestViewModel;
import com.lazycouple.restapiclient.util.BindingHolder;

import java.util.List;

/**
 * Created by noco on 2016-10-13.
 */
public class ReqParamAdapter extends RecyclerView.Adapter<ReqParamAdapter.ParameterHolder> {
    private final String TAG = ReqParamAdapter.class.getSimpleName();
    private Context mContext;
    private final RestRequestViewModel viewModel;

    public ReqParamAdapter(Context mContext, RestRequestViewModel viewModel) {
        Logger.d("ReqParamAdapter");
        this.mContext = mContext;
        this.viewModel = viewModel;
        setHasStableIds(true);
    }

    public Parameter getItem(int position) {
        return viewModel.getItem(position);
    }

    public List<Parameter> getItems() {
        return viewModel.getLists();
    }

    @Override
    public ParameterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Logger.d("onCreateViewHolder");
        ReqParametersRowBinding binding = ReqParametersRowBinding.inflate(LayoutInflater.from(mContext), parent, false);
        return new ParameterHolder(binding);
    }

    @Override
    public void onBindViewHolder(ParameterHolder holder, int position) {

        Parameter param = getItem(position);
        holder.bind(param);
        Logger.d("onBindViewHolder#posirion:"+position+"#param:"+param.toString());

    }

    @Override
    public int getItemCount() {
        return viewModel.getItemCount();
    }

    @Override
    public long getItemId(int position) {
        return viewModel.getItemId(position);
    }

    class ParameterHolder extends BindingHolder<Parameter> {
        public ParameterHolder(ViewDataBinding binding) {
            super(binding);
        }

        @Override
        public void bind(Parameter param) {
            binding.setVariable(BR.param, param);
        }
    }
}
