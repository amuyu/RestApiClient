package com.lazycouple.restapiclient.ui.adapter;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.amuyu.logger.Logger;
import com.android.databinding.library.baseAdapters.BR;
import com.lazycouple.restapiclient.databinding.ReqParametersRowBinding;
import com.lazycouple.restapiclient.ui.data.Parameter;
import com.lazycouple.restapiclient.util.BindingHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by noco on 2016-10-13.
 */
public class ReqParamAdapter extends RecyclerView.Adapter<ReqParamAdapter.ParameterHolder> {
    private final String TAG = ReqParamAdapter.class.getSimpleName();
    private Context mContext;
    private List<Parameter> parameters;

    public ReqParamAdapter(Context mContext) {
        Logger.d("ReqParamAdapter");
        this.mContext = mContext;
        parameters = new ArrayList<>();
    }

    public void add(Parameter param) {
        parameters.add(param);
    }

    public Parameter getItem(int position) {
        return parameters.get(position);
    }

    public List<Parameter> getItems() {
        return parameters;
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
        Logger.d("onBindViewHolder#param:"+param.toString());

    }

    @Override
    public int getItemCount() {
        return parameters.size();
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
