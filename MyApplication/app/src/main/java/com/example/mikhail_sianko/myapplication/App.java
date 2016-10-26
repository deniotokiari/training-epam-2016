package com.example.mikhail_sianko.myapplication;

import android.app.Application;
import android.os.AsyncTask;

import com.example.mikhail_sianko.myapplication.malevich.Malevich;
import com.example.mikhail_sianko.myapplication.utils.ContextGodObject;
import com.example.mikhail_sianko.myapplication.utils.ContextHolder;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

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

        ImageLoaderConfiguration config = ImageLoaderConfiguration.createDefault(this);

        ImageLoader.getInstance().init(config);
    }

}
