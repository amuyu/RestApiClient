package com.lazycouple.restapiclient.ui


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amuyu.logger.Logger
import com.lazycouple.restapiclient.R
import com.lazycouple.restapiclient.databinding.RequestFragmentBinding
import com.lazycouple.restapiclient.ui.adapter.ReqParamAdapter
import com.lazycouple.restapiclient.ui.component.DaggerRestRequestComponent
import com.lazycouple.restapiclient.ui.contract.RestRequestContract
import com.lazycouple.restapiclient.ui.data.CustomResponse
import com.lazycouple.restapiclient.ui.module.RestRequestModule
import com.lazycouple.restapiclient.ui.presenter.RestRequestPresenter
import com.lazycouple.restapiclient.ui.viewModel.RestRequestViewModel
import javax.inject.Inject

/**
 * Created by noco on 2016-10-12.
 */
class RestRequestFragment : Fragment(), RestRequestContract.View {
    private val TAG = RestRequestFragment::class.java.simpleName


    @Inject lateinit var restRequestPresenter: RestRequestPresenter

    internal lateinit var binding: RequestFragmentBinding
    internal lateinit var paramAdapter: ReqParamAdapter
    internal var id: String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Logger.d("Dagger")
//        val viewModel = ViewModelProviders.of(this).get(RestRequestViewModel::class.java)
        val viewModel = RestRequestViewModel()
        DaggerRestRequestComponent.builder()
                .restRequestModule(RestRequestModule(this, context, viewModel))
                .build().inject(this);
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Logger.d("")
        binding = RequestFragmentBinding.inflate(inflater, container, false)
        binding.view = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Logger.d("")
        binding.viewModel = restRequestPresenter!!.viewModel
        paramAdapter = ReqParamAdapter(activity, restRequestPresenter!!.viewModel)
        binding.rvParameters.layoutManager = LinearLayoutManager(activity)
        binding.rvParameters.adapter = paramAdapter
        restRequestPresenter!!.init(id)
    }


    fun onBackPressed(): Boolean {
        return restRequestPresenter!!.onBackPressed()
    }

    override fun sendRequest() {
        Logger.d("")
        restRequestPresenter!!.requestRestApi(binding.etInputUrl.text.toString(), paramAdapter.items)
    }

    override fun changeMethod() {
        Logger.d("")
        val method = binding.tvInputMethod.text.toString()
        if (method == RestRequestPresenter.Method.GET.name)
            restRequestPresenter!!.method = RestRequestPresenter.Method.POST
        else if (method == RestRequestPresenter.Method.POST.name)
            restRequestPresenter!!.method = RestRequestPresenter.Method.GET
    }

    override fun showResponse(response: CustomResponse?) {
        Logger.d("")

        if(response != null) {
            val fragment = fragmentManager.findFragmentById(R.id.content_frame) as RequestMainFragment
            fragment.showResponse(response)
        }
    }

    fun setId(id: String) {
        this.id = id
    }

    override fun refresh() {
        Logger.d("")
        paramAdapter.notifyDataSetChanged()
    }


    override fun showError() {
        // nothing
    }



    override fun onDestroy() {
        restRequestPresenter!!.destroy()
        super.onDestroy()
    }

    companion object {

        fun newInstance(): Fragment {
            val fragment = RestRequestFragment()
            return fragment
        }
    }

}
