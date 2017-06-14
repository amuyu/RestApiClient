package com.lazycouple.restapiclient.ui.component;

import com.lazycouple.restapiclient.ui.RequestHistoryFragmentList;
import com.lazycouple.restapiclient.ui.module.RequestHistoryModule;

import dagger.Component;

/**
 * Created by noco on 2016-10-27.
 */
@Component(modules = {RequestHistoryModule.class})
public interface RequestHistoryComponent2 {
    void inject(RequestHistoryFragmentList fragment);
}
