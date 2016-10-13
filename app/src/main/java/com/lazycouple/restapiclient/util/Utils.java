package com.lazycouple.restapiclient.util;

import android.net.Uri;

import com.lazycouple.restapiclient.ui.data.Parameter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by noco on 2016-10-13.
 */
public class Utils {

    public static String getBaseUrl(String url)     {
        return url.substring(0, (url.length() - Uri.parse(url).getPath().length()));
    }

    public static String getPath(String url) {
        return Uri.parse(url).getPath();
    }

    public static Map<String, String> mapParameters(List<Parameter> params) {
        Map<String, String> map = new HashMap<>();
        for (Parameter param : params) map.put(param.getKey(), param.getValue());
        return map;
    }
}
