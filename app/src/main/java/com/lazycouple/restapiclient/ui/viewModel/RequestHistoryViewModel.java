package com.lazycouple.restapiclient.ui.viewModel;

import com.lazycouple.restapiclient.db.model.Api;

/**
 * Created by amuyu on 2017. 6. 13..
 */

public class RequestHistoryViewModel extends AdapterViewModel<Api> {
    private boolean init = false;

    public boolean isInit() {
        return init;
    }

    public void setInit(boolean init) {
        this.init = init;
    }
}
