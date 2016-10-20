package com.example.mikhail_sianko.myapplication.threads.operation;

import java.net.HttpURLConnection;

public interface IRequestBuilder {
    void buildRequest(HttpURLConnection httpURLConnection);
}
