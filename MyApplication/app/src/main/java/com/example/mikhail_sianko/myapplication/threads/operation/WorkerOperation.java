package com.example.mikhail_sianko.myapplication.threads.operation;

import android.util.Log;

import com.example.mikhail_sianko.myapplication.threads.Operation;
import com.example.mikhail_sianko.myapplication.threads.ProgressCallback;

import java.io.IOException;

public class WorkerOperation implements Operation<String, Integer, WorkerOperation.Result> {

    public static final int N = 3;

    @Override
    public Result doing(final String whatYouDoing, final ProgressCallback<Integer> progressCallback) throws Exception {
        for (int i = 0; i < N; i++) {
            try {
                Thread.currentThread().sleep(1000L);
            } catch (InterruptedException e) {
                throw new IOException(e);
            }
            Log.d(TAG, whatYouDoing + i);
            progressCallback.onProgressChanged(i);
        }
        Result result = new Result();
        result.n = N;
        result.result = "I did " + whatYouDoing + N + " sec ";
        return  result;
    }

    public static class Result {
        private int n;

        private String result;

        public int getN() {
            return n;
        }

        public String getResult() {
            return result;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "n=" + n +
                    ", result='" + result + '\'' +
                    '}';
        }
    }

    public static final String TAG = WorkerOperation.class.getSimpleName();

}
