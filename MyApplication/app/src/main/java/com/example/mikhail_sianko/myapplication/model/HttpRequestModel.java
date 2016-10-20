package com.example.mikhail_sianko.myapplication.model;

import java.util.Map;

public class HttpRequestModel {

    private String url;

    private Map<String, String> headers;

    private String body;


    public void setUrl(String url) {
        this.url = url;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getUrl() {
        return url;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }
}
