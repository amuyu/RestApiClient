package com.amuyu.testrestapi.util;

import android.net.Uri;

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

}
