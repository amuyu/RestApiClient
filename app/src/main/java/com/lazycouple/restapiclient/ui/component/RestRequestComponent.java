package com.lazycouple.restapiclient.ui.component;

import com.lazycouple.restapiclient.ui.RestRequestFragment;
import com.lazycouple.restapiclient.ui.module.RestRequestModule;

import dagger.Component;

/**
 * Created by noco on 2016-10-12.
 */
@Component(modules = {RestRequestModule.class})
public interface RestRequestComponent {
    void inject(RestRequestFragment fragment);
}
