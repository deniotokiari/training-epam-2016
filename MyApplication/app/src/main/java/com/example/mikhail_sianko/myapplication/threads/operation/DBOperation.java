package com.example.mikhail_sianko.myapplication.threads.operation;

import android.util.Log;

import com.example.mikhail_sianko.myapplication.threads.Operation;
import com.example.mikhail_sianko.myapplication.threads.ProgressCallback;

public class DBOperation implements Operation<Integer, Integer, String> {

    public static final String TAG = DBOperation.class.getSimpleName();

    @Override
    public String doing(final Integer count, final ProgressCallback<Integer> progressCallback) throws Exception {
        for (int i = 0; i < count; i++) {
            Thread.currentThread().sleep(1000L);
            Log.d(TAG, "I'm sleeping " + i);
            progressCallback.onProgressChanged(i);
        }
        return "I slept " + count + " sec " ;
    }
}
