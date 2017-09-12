package com.lazycouple.restapiclient.ui.common


import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.lazycouple.restapiclient.MainActivity
import com.lazycouple.restapiclient.R
import com.lazycouple.restapiclient.ui.RequestHistoryFragment
import com.lazycouple.restapiclient.ui.RequestMainFragment

/**
 * Created by amuyu on 2017. 7. 14..
 */
class NavigationController(mainActivity: MainActivity) {

    val containerId: Int
    val fragmentManager: FragmentManager

    init {
        containerId = R.id.content_frame
        fragmentManager = mainActivity.supportFragmentManager
    }

    fun navigateToRequestMain() = navigateToRequestMain(null)

    fun navigateToRequestMain(id: String?) {
        switchFragment(RequestMainFragment().apply {
            id?.let { setId(id) }
        })
    }

    fun navigateToHistory() {
        switchFragment(RequestHistoryFragment())
    }

    private fun switchFragment(fragment: Fragment) {
        fragmentManager.beginTransaction().apply {
            replace(containerId, fragment)
            commit()
        }
    }

}