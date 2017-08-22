package com.lazycouple.restapiclient.data


import com.lazycouple.restapiclient.Injection
import com.lazycouple.restapiclient.deligate.MyApplication
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideRestRepository(app: MyApplication): RestRepository {
        return Injection.provideRestRepository(app)
    }
}
