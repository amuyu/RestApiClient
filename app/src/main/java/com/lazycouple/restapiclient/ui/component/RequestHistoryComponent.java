package com.lazycouple.restapiclient.ui.component;

import com.lazycouple.restapiclient.ui.RequestHistoryFragment;
import com.lazycouple.restapiclient.ui.contract.RequestHistoryContract;
import com.lazycouple.restapiclient.ui.module.RequestHistoryModule;
import com.lazycouple.restapiclient.ui.module.RestRequestModule;

import dagger.Component;

/**
 * Created by noco on 2016-10-27.
 */
@Component(modules = {RequestHistoryModule.class})
public interface RequestHistoryComponent {
    void inject(RequestHistoryFragment fragment);
}
