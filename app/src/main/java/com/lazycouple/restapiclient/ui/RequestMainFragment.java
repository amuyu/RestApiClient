package com.lazycouple.restapiclient.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amuyu.logger.Logger;
import com.lazycouple.restapiclient.databinding.RequestMainRBinding;
import com.lazycouple.restapiclient.ui.base.BaseFragment;
import com.lazycouple.restapiclient.ui.data.CustomResponse;

import static com.lazycouple.restapiclient.ui.RestResponseFragment.KEY;

/**
 * Created by noco on 2016-10-12.
 */
public class RequestMainFragment extends BaseFragment {
    private final String TAG = RequestMainFragment.class.getSimpleName();

    RequestMainRBinding binding;
    TabsAdapter tabsAdapter;

    String id = null;

    public static Fragment newInstance() {
        Fragment fragment = new RequestMainFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Logger.d("");

        initTabLayout(true);
        binding = RequestMainRBinding.inflate(inflater, container, false);

        TabLayout tabLayout = getTabLayout();
        tabLayout.addTab(tabLayout.newTab().setText("Request"));
        tabLayout.addTab(tabLayout.newTab().setText("Response"));

        tabsAdapter = new TabsAdapter(getFragmentManager());
        binding.viewPager.setAdapter(tabsAdapter);
        binding.viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Logger.d("");

    }

    public boolean onBackPressed()
    {
        Logger.d("");
        return false;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void showResponse(CustomResponse response) {
        Logger.d("");
        tabsAdapter.setCustomResponse(response);
        binding.viewPager.setCurrentItem(1);
        tabsAdapter.notifyDataSetChanged();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private class TabsAdapter extends FragmentStatePagerAdapter {
        private final int TAB_COUNT = 2;
        private CustomResponse customResponse;

        public TabsAdapter(FragmentManager fm) {
            super(fm);
        }

        public void setCustomResponse(CustomResponse customResponse) {
            this.customResponse = customResponse;
        }

        @Override
        public Fragment getItem(int position) {
            if(position == 0) {
                Fragment fragment = RestRequestFragment.newInstance();
                return fragment;
            } else {
                Fragment fragment = RestResponseFragment.newInstance();
                if(customResponse != null) {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable(KEY, customResponse);
                    fragment.setArguments(bundle);
                }
                return fragment;
            }
        }

        @Override
        public int getCount() {
            return TAB_COUNT;
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
    }
}
