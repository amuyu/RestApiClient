package com.lazycouple.restapiclient.ui.base;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.View;

import com.lazycouple.restapiclient.R;

/**
 * Created by amuyu on 2017. 6. 21..
 */

public class BaseFragment extends Fragment {


    private TabLayout tabLayout;

    public void initTabLayout(boolean visible) {
        tabLayout = (TabLayout)getActivity().findViewById(R.id.tabLayout);
        if(useTabLayout()) {
            setTabVisible(visible);
            tabLayout.removeAllTabs();
            tabLayout.clearOnTabSelectedListeners();
        }
    }


    public void setTabVisible(boolean visible) {
        tabLayout.setVisibility(visible?View.VISIBLE:View.GONE);
    }

    public TabLayout getTabLayout() {
        return tabLayout;
    }

    public boolean useTabLayout() {
        return (tabLayout!=null);
    }
}
