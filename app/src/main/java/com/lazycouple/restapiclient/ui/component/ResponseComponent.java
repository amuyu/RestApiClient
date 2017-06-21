package com.lazycouple.restapiclient.ui.component;

import com.lazycouple.restapiclient.ui.RestResponseFragment;
import com.lazycouple.restapiclient.ui.module.ResponseModule;

import dagger.Component;

/**
 * Created by amuyu on 2017. 6. 21..
 */
@Component(modules = {ResponseModule.class})
public interface ResponseComponent {
    void inject(RestResponseFragment fragment);


}
