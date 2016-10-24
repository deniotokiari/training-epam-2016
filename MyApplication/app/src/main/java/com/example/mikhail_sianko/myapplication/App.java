package com.example.mikhail_sianko.myapplication;

import android.app.Application;
import android.os.AsyncTask;

import com.example.mikhail_sianko.myapplication.malevich.Malevich;
import com.example.mikhail_sianko.myapplication.utils.ContextGodObject;
import com.example.mikhail_sianko.myapplication.utils.ContextHolder;

/**
 * Created by Alex Dzeshko on 30-Sep-16.
 */

public class App extends Application {

    private Malevich malevich;

    public Malevich getMalevich() {
        if (malevich == null) {
            malevich = Malevich.Impl.newInstance();
        }
        return malevich;
    }

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
