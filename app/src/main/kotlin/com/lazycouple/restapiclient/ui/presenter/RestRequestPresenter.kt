package com.lazycouple.restapiclient.ui.presenter

import android.content.Context
import com.amuyu.logger.Logger
import com.amuyu.testrestapi.network.DataManager
import com.amuyu.testrestapi.network.DataManagerImpl
import com.lazycouple.restapiclient.data.RestRepository
import com.lazycouple.restapiclient.db.model.Parameter
import com.lazycouple.restapiclient.ui.contract.RestRequestContract
import com.lazycouple.restapiclient.ui.data.CustomResponse
import com.lazycouple.restapiclient.ui.viewModel.RestRequestViewModel
import com.lazycouple.restapiclient.util.ConfigProperties
import com.lazycouple.restapiclient.util.Utils
import io.realm.Realm
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import rx.Subscriber
import java.io.IOException
import java.util.*
import javax.inject.Inject


/**
 * Created by noco on 2016-10-12.
 */
class RestRequestPresenter @Inject
constructor(private val context: Context, private val view: RestRequestContract.View,
            val viewModel: RestRequestViewModel, private val repository: RestRepository) : RestRequestContract.Presenter {
    private val dataManager: DataManager
    private val realm: Realm

    enum class Method private constructor(var value: Int) {
        GET(0), POST(1);


        companion object {

            fun newInstance(value: Int): Method {
                if (value == GET.value)
                    return GET
                else if (value == POST.value)
                    return POST
                else
                    return GET
            }
        }
    }


    init {
        this.dataManager = DataManagerImpl()
        realm = Realm.getDefaultInstance()
    }

    override fun init(historyName: String?) {
        Logger.d("");
        if (viewModel.url == null) loadData(historyName)
        else {
            Logger.d("url is not null");
        }
    }

    override fun loadData(id: String?) {
        Logger.d("historyName:" + id)
        if (id != null) {
            repository.getApi(realm, id).subscribe { api ->
                if (api != null) {
                    Logger.d("" + api.toString())
                    var url = api.url
                    val params = api.parameters
                    for (param in params) {
                        if (param.key != "url")
                            viewModel.addItem(param)
                        else
                            url = param.value
                    }
                    viewModel.method = Method.newInstance(api.method)
                    showData(url)
                }
            }
        } else {
            Logger.d("history is null")
            var url: String? = null
            val params = ConfigProperties.getApiInfo(context, "fcmTest")
            for (param in params) {
                if (param.key != "url")
                    viewModel.addItem(param)
                else
                    url = param.value
            }
            viewModel.method = Method.POST
            showData(url)
        }

    }

    private fun showData(url: String?) {
        Logger.d("refresh#url:" + url)
        Logger.d(viewModel.itemCount)
        viewModel.url = url
        view.refresh()
    }


    override fun requestRestApi(url: String, parameters: List<Parameter>) {
        val temp = HashMap<String, String>()
        temp.put("Authorization", "Key=AIzaSyDuiS6N5E3lksUgwwIvd8_9Vj3PjJIcET8")

        repository.addApi(url, viewModel.method, parameters)
        viewModel.isRequest = true
        when (viewModel.method) {
            RestRequestPresenter.Method.GET -> {
                val map = Utils.mapParameters(parameters)
                requestGet(url, map, temp)
            }
            RestRequestPresenter.Method.POST -> {
                val body = Utils.bodyParameters(parameters)
                requestPost(url, body, temp)
            }
        }


    }

    override var method: Method
        get() = viewModel.method
        set(method) {
            viewModel.method = method
        }

    override fun onBackPressed(): Boolean {

        if (viewModel.isRequest) {
            viewModel.isRequest = false
            return false
        }

        return true
    }

    override fun destroy() {
        realm.close()
    }

    private fun requestGet(url: String, map: Map<String, String>, headerMap: Map<String, String>) {
        dataManager.callApi(url, map, headerMap)!!
                .subscribe(object : Subscriber<Response<ResponseBody>>() {
                    override fun onCompleted() {

                    }

                    override fun onError(e: Throwable) {
                        view.showError()
                    }

                    override fun onNext(response: Response<ResponseBody>) {


                        var customResponse: CustomResponse? = null
                        if (response.code() == 200) {
                            Logger.d("body : " + response.body().toString())

                            try {
                                customResponse = CustomResponse.CustomResponseBuilder()
                                        .code(response.code())
                                        .body(response.body().string())
                                        .build()
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }

                        } else {
                            try {
                                customResponse = CustomResponse.CustomResponseBuilder()
                                        .code(response.code())
                                        .errorbody(response.errorBody().string())
                                        .build()
                            } catch (e: IOException) {
                                e.printStackTrace()
                            }

                        }

                        //                        viewModel.setResponse(customResponse);
                        view.showResponse(customResponse)
                    }
                })
    }

    private fun requestPost(url: String, body: RequestBody?, headerMap: Map<String, String>) {
        if(body != null) {
            dataManager.callApiPost(url, body, headerMap)!!
                    .subscribe(object : Subscriber<Response<ResponseBody>>() {
                        override fun onCompleted() {

                        }

                        override fun onError(e: Throwable) {
                            view.showError()
                        }

                        override fun onNext(response: Response<ResponseBody>) {


                            var customResponse: CustomResponse? = null
                            if (response.code() == 200) {
                                Logger.d("body : " + response.body().toString())

                                try {
                                    customResponse = CustomResponse.CustomResponseBuilder()
                                            .code(response.code())
                                            .body(response.body().string())
                                            .build()
                                } catch (e: IOException) {
                                    e.printStackTrace()
                                }

                            } else {
                                try {
                                    customResponse = CustomResponse.CustomResponseBuilder()
                                            .code(response.code())
                                            .errorbody(response.errorBody().string())
                                            .build()
                                } catch (e: IOException) {
                                    e.printStackTrace()
                                }

                            }


                            //                        viewModel.setResponse(customResponse);
                            view.showResponse(customResponse)
                        }
                    })
        }

    }


    override fun start(context: Context) {

    }


}
