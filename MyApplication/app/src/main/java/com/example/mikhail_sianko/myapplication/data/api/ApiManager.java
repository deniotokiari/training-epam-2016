package com.example.mikhail_sianko.myapplication.data.api;

import com.example.mikhail_sianko.myapplication.BuildConfig;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.training2016.backend.trainingApi.TrainingApi;

import java.io.IOException;

public class ApiManager {

    public static final String APP_ENGINE_BASE_URL = "http://10.0.2.2:8080/_ah/api/";

    private static ApiManager sInstance;
    private TrainingApi appEngineApi;

    public static ApiManager get() {
        if (sInstance == null) {
            sInstance = new ApiManager();
        }
        return sInstance;
    }

    private ApiManager() {
    }

    public TrainingApi trainingsApi() {
        if (appEngineApi == null) {
            TrainingApi.Builder builder = new TrainingApi.Builder(AndroidHttp.newCompatibleTransport(),
                    JacksonFactory.getDefaultInstance(), null)
                    .setApplicationName(BuildConfig.APPLICATION_ID)
                    .setRootUrl(APP_ENGINE_BASE_URL)
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            appEngineApi = builder.build();
        }
        return appEngineApi;
    }
}
