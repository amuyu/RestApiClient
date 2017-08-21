package com.lazycouple.restapiclient.ui.component

import com.lazycouple.restapiclient.ui.RestResponseFragment
import com.lazycouple.restapiclient.ui.module.ResponseModule

import dagger.Component

/**
 * Created by amuyu on 2017. 6. 21..
 */
@Component(modules = arrayOf(ResponseModule::class))
interface ResponseComponent {
    fun inject(fragment: RestResponseFragment)


}
