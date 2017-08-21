package com.lazycouple.restapiclient.ui.module

import android.content.Context

import dagger.Module
import dagger.Provides

/**
 * Created by amuyu on 2017. 6. 21..
 */
@Module
class ContextModule(private val context: Context) {

    @Provides
    fun provideContext(): Context {
        return this.context
    }
}
