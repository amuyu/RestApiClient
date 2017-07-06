package com.amuyu.testrestapi;

import android.content.Context;

import com.amuyu.logger.DefaultLogPrinter;
import com.amuyu.logger.Logger;


/**
 * Created by noco on 2016-10-12.
 */
public class TestRestApi {

    public static void init(Context context) {
        Logger.addLogPrinter(new DefaultLogPrinter(context));
    }
}
