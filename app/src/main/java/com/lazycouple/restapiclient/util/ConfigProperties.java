package com.lazycouple.restapiclient.util;

import android.content.Context;
import android.content.res.AssetManager;

import com.lazycouple.restapiclient.R;
import com.lazycouple.restapiclient.ui.data.Parameter;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Created by noco on 2016-10-13.
 */
public class ConfigProperties {
    private final static String TAG = ConfigProperties.class.getSimpleName();

    public static List<Parameter> getApiInfo(Context context, String arrayName) {

        int id = context.getResources().getIdentifier("versionCheck", "array", context.getPackageName());
        String[] array = context.getResources().getStringArray(id);

        Logger.d(TAG, "array :" + Arrays.toString(array));
        List<Parameter> params = new ArrayList<>();
        for(String str : array)
        {
            String[] split = str.split("\\|");
            Logger.d(TAG, "length :" + split.length);
            Logger.d(TAG, "[0] :" + split[0]);
            Logger.d(TAG, "[1] :" + split[0]);
            params.add(new Parameter(split[0], split[1]));
        }
        return params;
    }

}
