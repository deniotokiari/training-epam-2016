package com.example.mikhail_sianko.myapplication;

import android.app.Application;
import android.net.http.HttpResponseCache;
import android.os.AsyncTask;

import com.example.mikhail_sianko.myapplication.utils.ContextGodObject;
import com.example.mikhail_sianko.myapplication.utils.ContextHolder;

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

        AsyncTask.SERIAL_EXECUTOR.execute(new Runnable() {

            @Override
            public void run() {
                //init libraries
            }
        });

        ContextHolder.set(this);
        ContextGodObject.getInstance().setContext(this);
    }

    @Override
    public void unregisterActivityLifecycleCallbacks(ActivityLifecycleCallbacks callback) {
        super.unregisterActivityLifecycleCallbacks(callback);
    }
}
