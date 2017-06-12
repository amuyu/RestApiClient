package com.lazycouple.restapiclient.ui.viewModel;

import android.databinding.BaseObservable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amuyu on 2017. 6. 12..
 */

public abstract class AdapterViewModel<T> extends BaseObservable {
    List<T> lists = new ArrayList<T>();

    public List<T> getLists() {
        return lists;
    }

    public void setLists(List<T> lists) {
        this.lists = lists;
    }

    public void addItem(T item) {
        lists.add(item);
    }

    public int getItemCount() {
        return lists.size();
    }

    public long getItemId(int position) {
        return lists.get(position).hashCode();
    }

    public T getItem(int position) {
        return lists.get(position);
    }

    public void clearLists() {
        lists.clear();
    }
}
