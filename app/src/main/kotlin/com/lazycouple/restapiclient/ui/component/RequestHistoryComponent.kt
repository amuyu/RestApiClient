package com.lazycouple.restapiclient.ui.component

import com.lazycouple.restapiclient.ui.RequestHistoryFragment
import com.lazycouple.restapiclient.ui.module.RequestHistoryModule
import dagger.Subcomponent

/**
 * Created by noco on 2016-10-27.
 */
@Subcomponent(modules = arrayOf(RequestHistoryModule::class))
interface RequestHistoryComponent {
    fun inject(fragment: RequestHistoryFragment)
}
