package com.lazycouple.restapiclient.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.amuyu.logger.Logger;
import com.lazycouple.restapiclient.R;
import com.lazycouple.restapiclient.ui.data.Parameter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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
        View v = LayoutInflater.from(mContext).inflate(R.layout.req_parameters_row, parent, false);
        ParameterHolder adapterHolder = new ParameterHolder(v);
        ButterKnife.bind(adapterHolder, v);
        return adapterHolder;
    }

    @Override
    public void onBindViewHolder(ParameterHolder holder, int position) {

        Parameter param = getItem(position);
        Logger.d("onBindViewHolder#param:"+param.toString());

        holder.et_param_key.setText(param.getKey());
        holder.et_param_value.setText(param.getValue());
    }

    @Override
    public int getItemCount() {
        return parameters.size();
    }

    static class ParameterHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.et_param_key) EditText et_param_key;
        @BindView(R.id.et_param_value) EditText et_param_value;

        public ParameterHolder(View itemView) {
            super(itemView);
        }
    }
}
