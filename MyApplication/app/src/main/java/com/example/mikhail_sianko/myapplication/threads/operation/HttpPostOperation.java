package com.example.mikhail_sianko.myapplication.threads.operation;

import com.example.mikhail_sianko.myapplication.model.HttpRequestModel;
import com.example.mikhail_sianko.myapplication.threads.Operation;
import com.example.mikhail_sianko.myapplication.threads.ProgressCallback;
import com.training2016.android.http.HttpClient;

public class HttpPostOperation implements Operation<HttpRequestModel, Void, String> {

    @Override
    public String doing(HttpRequestModel inputRequestModel, ProgressCallback<Void> progressCallback) throws Exception {
        HttpClient httpClient = new HttpClient();
        if (inputRequestModel != null) {
            return httpClient.post(inputRequestModel.getUrl(), inputRequestModel.getHeaders(), inputRequestModel.getBody());
        } else {
            throw new IllegalArgumentException("httpRequestModel is null");
        }
    }
}
