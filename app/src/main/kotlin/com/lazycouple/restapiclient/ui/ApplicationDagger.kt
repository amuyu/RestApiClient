package com.lazycouple.restapiclient.ui

import com.lazycouple.restapiclient.data.RepositoryModule
import com.lazycouple.restapiclient.deligate.MyApplication
import com.lazycouple.restapiclient.ui.component.RequestHistoryComponent
import com.lazycouple.restapiclient.ui.component.RestRequestComponent
import com.lazycouple.restapiclient.ui.module.RequestHistoryModule
import com.lazycouple.restapiclient.ui.module.RestRequestModule
import com.lazycouple.restapiclient.util.ApplicationScope
import dagger.Component
import dagger.Module
import dagger.Provides

@ApplicationScope
@Component(modules = arrayOf(ApplicationModule::class,
        RepositoryModule::class))
interface ApplicationComponent {
    fun inject(application: MyApplication)
    fun restRequestComponent(restRequestModule: RestRequestModule): RestRequestComponent
    fun requestHistoryComponent(requestHistoryModule: RequestHistoryModule): RequestHistoryComponent
}

@ApplicationScope
@Module
class ApplicationModule(private val application: MyApplication ) {
    @Provides fun application()  = application
}