package com.lazycouple.restapiclient.ui.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by amuyu on 2017. 6. 21..
 */
@Module
public class ContextModule {

    private final Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context provideContext() {
        return this.context;
    }
}
