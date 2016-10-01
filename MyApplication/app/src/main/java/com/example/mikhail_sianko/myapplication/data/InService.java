package com.example.mikhail_sianko.myapplication.data;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by Alex Dzeshko on 30-Sep-16.
 */

public class InService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public InService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }
}
