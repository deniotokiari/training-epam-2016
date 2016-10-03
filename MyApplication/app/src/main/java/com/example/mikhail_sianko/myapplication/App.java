package com.example.mikhail_sianko.myapplication;

import android.app.Application;

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
