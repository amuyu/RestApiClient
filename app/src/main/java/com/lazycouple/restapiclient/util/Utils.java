package com.lazycouple.restapiclient.util;

import android.net.Uri;

import com.lazycouple.restapiclient.db.model.Parameter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

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

    public static RequestBody bodyParameters(List<Parameter> params) {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        JSONObject object = paramToJson(params);
        return (object != null)?RequestBody.create(JSON, object.toString()):null;
    }

    public static JSONObject paramToJson(List<Parameter> params) {
        JSONObject object = new JSONObject();
        try {
            for (Parameter param : params) {
                String value = param.getKey();
                try {
                    JSONObject sub = new JSONObject(param.getValue());
                    object.put(param.getKey(), sub);
                } catch (JSONException e) {
                    object.put(param.getKey(), param.getValue());
                }
            }
            return object;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
