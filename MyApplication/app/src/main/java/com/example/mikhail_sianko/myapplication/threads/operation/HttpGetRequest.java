package com.example.mikhail_sianko.myapplication.threads.operation;

import com.example.mikhail_sianko.myapplication.model.HttpRequestModel;
import com.example.mikhail_sianko.myapplication.threads.Operation;
import com.example.mikhail_sianko.myapplication.threads.ProgressCallback;
import com.training2016.android.http.HttpClient;

public class HttpGetRequest implements Operation<HttpRequestModel, Void, String> {

    public HttpGetRequest() {

    }

    @Override
    public String doing(HttpRequestModel requestModel, ProgressCallback<Void> progressCallback) throws Exception {
        HttpClient httpClient = new HttpClient();


        return httpClient.get(requestModel.getUrl(), requestModel.getHeaders());
    }
}
