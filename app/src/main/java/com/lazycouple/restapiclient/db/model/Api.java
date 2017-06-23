package com.lazycouple.restapiclient.db.model;

import java.util.Date;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by amuyu on 2017. 6. 16..
 */

public class Api extends RealmObject {
    @PrimaryKey
    private String id;
    private String url;
    private RealmList<Parameter> parameters;
    private Date date;
    private int Method;

    public Api() {
        parameters = new RealmList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public RealmList<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters.clear();
        for(Parameter param : parameters) {
            param.setId(this.id);
            this.parameters.add(param);
        }
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getMethod() {
        return Method;
    }

    public void setMethod(int method) {
        Method = method;
    }

    @Override
    public String toString() {
        return "Api{" +
                "id='" + id + '\'' +
                ", url='" + url + '\'' +
                ", parameters=" + parameters +
                ", date=" + date +
                '}';
    }
}
