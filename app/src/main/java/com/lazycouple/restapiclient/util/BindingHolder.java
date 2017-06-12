package com.lazycouple.restapiclient.util;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;



public abstract class BindingHolder<T> extends RecyclerView.ViewHolder {
    protected final ViewDataBinding binding;

    public BindingHolder(ViewDataBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public abstract void bind(T variable);

    public ViewDataBinding getBinding() {
        return binding;
    }
}
