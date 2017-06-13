package com.lazycouple.restapiclient;

import com.lazycouple.restapiclient.data.RestRepository;
import com.lazycouple.restapiclient.data.local.RestLocalDataSource;

/**
 * Created by amuyu on 2017. 6. 13..
 */

public class Injection {

    public static RestRepository provideRestRepository() {
        return RestRepository.getInstance(RestLocalDataSource.getInstance());
    }
}
