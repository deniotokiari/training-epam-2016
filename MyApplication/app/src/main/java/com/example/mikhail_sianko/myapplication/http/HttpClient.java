package com.example.mikhail_sianko.myapplication.http;

import java.io.InputStream;

public class HttpClient implements IHttpClient {

    public static final String HTTP_CLIENT = "HTTP_CLIENT";

    public HttpClient() {

    }

    @Override
    public InputStream get(IRequest pRequest) {
        return null;
    }

    @Override
    public InputStream post(IRequest pRequest) {
        return null;
    }
}
