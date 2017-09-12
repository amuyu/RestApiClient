package com.lazycouple.restapiclient.ui


import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amuyu.logger.Logger
import com.lazycouple.restapiclient.databinding.RequestMainRBinding
import com.lazycouple.restapiclient.ui.base.BaseFragment
import com.lazycouple.restapiclient.ui.data.CustomResponse

/**
 * Created by noco on 2016-10-12.
 */
class RequestMainFragment : BaseFragment() {
    private val TAG = RequestMainFragment::class.java.simpleName

    internal lateinit var binding: RequestMainRBinding
    private val tabsAdapter: TabsAdapter by lazy  {
        TabsAdapter(fragmentManager)
    }

    internal var id: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Logger.d("")

        initTabLayout(true)
        binding = RequestMainRBinding.inflate(inflater, container, false)


        tabLayout?.apply {
            addTab(newTab().setText("Request"))
            addTab(newTab().setText("Response"))
            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    binding.viewPager.currentItem = tab.position
                }

                override fun onTabUnselected(tab: TabLayout.Tab) {

                }

                override fun onTabReselected(tab: TabLayout.Tab) {

                }
            })
        }



        binding.viewPager.adapter = tabsAdapter
        binding.viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))


        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Logger.d("")

    }

    fun onBackPressed(): Boolean {
        Logger.d("")
        return false
    }

    fun setId(id: String) {
        Logger.d("");
        this.id = id
    }

    fun showResponse(response: CustomResponse?) {
        Logger.d("")
        tabsAdapter.run {
            binding.viewPager.currentItem = 1
            setCustomResponse(response)
            notifyDataSetChanged()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
    }

    private inner class TabsAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        private val TAB_COUNT = 2
        private var customResponse: CustomResponse? = null

        fun setCustomResponse(customResponse: CustomResponse?) {
            this.customResponse = customResponse
        }

        override fun getItem(position: Int): Fragment {
            if (position == 0) {
                return RestRequestFragment.newInstance()
            } else {
                val fragment = RestResponseFragment.newInstance()
                customResponse?.run {
                    val bundle = Bundle()
                    bundle.putParcelable(RestResponseFragment.KEY, customResponse)
                    fragment.arguments = bundle
                }
                return fragment
            }
        }

        override fun getCount(): Int {
            return TAB_COUNT
        }

        override fun getItemPosition(`object`: Any?): Int {
            return PagerAdapter.POSITION_NONE
        }
    }

    companion object {

        fun newInstance(): Fragment {
            val fragment = RequestMainFragment()
            return fragment
        }
    }
}
