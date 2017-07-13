package com.lazycouple.restapiclient.ui.base

import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.view.View

import com.lazycouple.restapiclient.R

/**
 * Created by amuyu on 2017. 6. 21..
 */

open class BaseFragment : Fragment() {


    var tabLayout: TabLayout? = null
        private set

    fun initTabLayout(visible: Boolean) {
        tabLayout = activity.findViewById(R.id.tabLayout) as TabLayout
        if (useTabLayout()) {
            setTabVisible(visible)
            tabLayout!!.removeAllTabs()
            tabLayout!!.clearOnTabSelectedListeners()
        }
    }


    fun setTabVisible(visible: Boolean) {
        tabLayout!!.visibility = if (visible) View.VISIBLE else View.GONE
    }

    fun useTabLayout(): Boolean {
        return tabLayout != null
    }
}
