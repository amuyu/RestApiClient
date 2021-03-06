package com.lazycouple.restapiclient.ui


import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import com.amuyu.logger.Logger
import com.lazycouple.restapiclient.MainActivity
import com.lazycouple.restapiclient.R
import com.lazycouple.restapiclient.databinding.HistoryMainBinding
import com.lazycouple.restapiclient.deligate.MyApplication
import com.lazycouple.restapiclient.ui.adapter.ReqHistoryAdapter
import com.lazycouple.restapiclient.ui.base.BaseFragment
import com.lazycouple.restapiclient.ui.contract.RequestHistoryContract
import com.lazycouple.restapiclient.ui.module.RequestHistoryModule
import com.lazycouple.restapiclient.ui.presenter.RequestHistoryPresenter
import com.lazycouple.restapiclient.ui.viewModel.RequestHistoryViewModel
import javax.inject.Inject

/**
 * Created by noco on 2016-10-13.
 */
class RequestHistoryFragment : BaseFragment(), RequestHistoryContract.View {
    private val TAG = RequestHistoryFragment::class.java.simpleName

    @Inject lateinit var requestHistoryPresenter: RequestHistoryPresenter


    lateinit var binding: HistoryMainBinding
    lateinit var historyAdapter: ReqHistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Logger.d("")
        val viewModel = ViewModelProviders.of(this).get(RequestHistoryViewModel::class.java)

        (activity.application as MyApplication)
                .appComponent
                .requestHistoryComponent(RequestHistoryModule(context, this, viewModel))
                .inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        historyAdapter = ReqHistoryAdapter(activity,
                                            requestHistoryPresenter.viewModel,
                                            requestHistoryPresenter as RequestHistoryContract.Presenter).apply {
                            binding.rvHistoryList.adapter = this
                        }
        binding.rvHistoryList.layoutManager = LinearLayoutManager(activity)
        binding.srHistoryRefresh.setOnRefreshListener {
            // refresh event
            Logger.d("refresh event")
            requestHistoryPresenter.loadList()
        }

        requestHistoryPresenter.init()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        initTabLayout(false)
        setHasOptionsMenu(true)
        binding = HistoryMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.history_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        val id = item?.itemId
        when (id) {
            R.id.action_remove -> {
                Logger.d("")
                requestHistoryPresenter.clearItems()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showList() {
        historyAdapter.notifyDataSetChanged()
    }

    override fun showRestRequset(id: String?) {
        id?.let {
            (activity as MainActivity).loadRequestFragment(id)
        }
    }

    override fun onDestroy() {
        requestHistoryPresenter.destroy()
        super.onDestroy()
    }

    companion object {

        fun newInstance(): Fragment {
            val fragment = RequestHistoryFragment()
            return fragment
        }
    }
}
