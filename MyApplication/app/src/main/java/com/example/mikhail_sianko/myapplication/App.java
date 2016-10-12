package com.example.mikhail_sianko.myapplication;

import android.app.Application;
import android.net.http.HttpResponseCache;

import java.io.File;
import java.io.IOException;
import java.net.ResponseCache;

/**
 * Created by Alex Dzeshko on 30-Sep-16.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void unregisterActivityLifecycleCallbacks(ActivityLifecycleCallbacks callback) {
        super.unregisterActivityLifecycleCallbacks(callback);
    }
}
