package com.lazycouple.restapiclient.ui


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amuyu.logger.Logger
import com.lazycouple.restapiclient.databinding.ResponseFragmentBinding
import com.lazycouple.restapiclient.ui.contract.ResponseContract
import com.lazycouple.restapiclient.ui.presenter.ResponsePresenter
import com.lazycouple.restapiclient.ui.viewModel.ResponseViewModel

/**
 * Created by noco on 2016-10-12.
 */
class RestResponseFragment : Fragment(), ResponseContract.View {


    lateinit var presenter: ResponsePresenter
    internal lateinit var binding: ResponseFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val viewModel = ViewModelProviders.of(this).get(ResponseViewModel::class.java)
        val viewModel = ResponseViewModel()
        presenter = ResponsePresenter(context, this, viewModel)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Logger.d("")
        binding = ResponseFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = presenter!!.viewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Logger.d("")
        if(arguments != null) presenter!!.init(arguments)
    }

    fun onBackPressed(): Boolean {
        return false
    }


    companion object {
        var KEY = "RESPONSE"


        fun newInstance(): Fragment {
            val fragment = RestResponseFragment()
            return fragment
        }
    }


}
