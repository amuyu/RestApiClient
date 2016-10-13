package com.lazycouple.restapiclient.ui.data;

import okhttp3.FormBody;

/**
 * Created by noco on 2016-10-13.
 */
public class Parameter {
    private String key;
    private String value;

    public Parameter() {
    }

    public Parameter(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    public static final class Builder {
        private String key;
        private String value;


        public Builder key(String key) {
            this.key = key;
            return this;
        }

        public Builder value(String value) {
            this.value = value;
            return this;
        }

        public Parameter build() {
            Parameter parameter = new Parameter();
            parameter.setKey(key);
            parameter.setValue(value);
            return parameter;
        }
    }
}
