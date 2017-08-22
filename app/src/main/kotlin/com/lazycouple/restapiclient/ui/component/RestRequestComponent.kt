package com.lazycouple.restapiclient.ui.component

import com.lazycouple.restapiclient.ui.RestRequestFragment
import com.lazycouple.restapiclient.ui.module.RestRequestModule
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(RestRequestModule::class))
interface RestRequestComponent {
    fun inject(fragment: RestRequestFragment)
}
