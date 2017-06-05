package com.lazycouple.restapiclient.deligate;

import android.app.Application;

import com.amuyu.logger.DefaultLogPrinter;
import com.amuyu.logger.Logger;


/**
 * Created by noco on 2016-10-12.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.addLogPrinter(new DefaultLogPrinter(this));
    }
}
