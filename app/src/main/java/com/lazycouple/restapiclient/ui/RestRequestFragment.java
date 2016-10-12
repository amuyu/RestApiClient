package com.lazycouple.restapiclient.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lazycouple.restapiclient.R;
import com.lazycouple.restapiclient.util.Logger;

/**
 * Created by noco on 2016-10-12.
 */
public class RestRequestFragment extends Fragment{
    private final String TAG = RestRequestFragment.class.getSimpleName();

    public static Fragment newInstance() {
        Fragment fragment = new RestRequestFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Logger.d(TAG, "onCreateView");
        View rootView = inflater.inflate(R.layout.request_main, container, false);
        return rootView;
    }
}