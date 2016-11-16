package com.lazycouple.restapiclient.util;



import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.internal.Util;

/**
 * Created by noco on 2016-09-01.
 */
public class OkHttpFormBuilder {

    private MediaType CONTENT_TYPE = MediaType.parse("application/x-www-form-urlencoded;charset=utf-8");
    private final StringBuilder content = new StringBuilder();


    public OkHttpFormBuilder() {
    }

    public MediaType getCONTENT_TYPE() {
        return CONTENT_TYPE;
    }

    public void setCONTENT_TYPE(MediaType CONTENT_TYPE) {
        this.CONTENT_TYPE = CONTENT_TYPE;
    }

    public OkHttpFormBuilder add(String name, String value) {
        if(this.content.length() > 0) {
            this.content.append('&');
        }

        try {
            this.content.append(URLEncoder.encode(name, "UTF-8")).append('=').append(URLEncoder.encode(value, "UTF-8"));
            return this;
        } catch (UnsupportedEncodingException var4) {
            throw new AssertionError(var4);
        }
    }

    public String getContent()
    {
        return this.content.toString();
    }

    public RequestBody build() {
        if(this.content.length() == 0) {
            throw new IllegalStateException("Form encoded body must have at least one part.");
        } else {
            byte[] contentBytes = this.content.toString().getBytes(Util.UTF_8);
            return RequestBody.create(CONTENT_TYPE, contentBytes);
        }
    }
}
