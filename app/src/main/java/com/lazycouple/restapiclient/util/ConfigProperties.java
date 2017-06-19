package com.lazycouple.restapiclient.util;

import android.content.Context;

import com.lazycouple.restapiclient.R;
import com.lazycouple.restapiclient.db.model.Parameter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by noco on 2016-10-13.
 */
public class ConfigProperties {
    private final static String TAG = ConfigProperties.class.getSimpleName();

    public static List<Parameter> getApiInfo(Context context, String arrayName) {

        int id = context.getResources().getIdentifier(arrayName, "array", context.getPackageName());
        String[] array = context.getResources().getStringArray(id);

        List<Parameter> params = new ArrayList<>();
        for(String str : array)
        {
            String[] split = str.split("\\|");
            params.add(new Parameter(split[0], split[1]));
        }
        return params;
    }

    public static List<String> getHistories(Context context) {
        String[] array = context.getResources().getStringArray(R.array.history);
        List<String> histories = new ArrayList<String>(Arrays.asList(array));
        return histories;
    }

}
