package com.example.mikhail_sianko.myapplication.threads;

import android.os.Handler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadManager {

    public static final int COUNT_CORE = 3;
    private final ExecutorService executorService;

    public ThreadManager() {
        this.executorService = Executors.newFixedThreadPool(COUNT_CORE);
    }

    public<Params, Progress, Result> void execute(final Operation<Params, Progress, Result> operation, final Params param, final OnResultCallback<Result, Progress> onResultCallback) {
        final Handler handler = new Handler();
        executorService.execute(new Runnable() {

            @Override
            public void run() {
                try {
                    final Result result = operation.doing(param, new ProgressCallback<Progress>() {

                        @Override
                        public void onProgressChanged(final Progress progress) {
                            handler.post(new Runnable() {

                                @Override
                                public void run() {
                                    onResultCallback.onProgressChanged(progress);
                                }
                            });
                        }
                    });
                    handler.post(new Runnable() {

                        @Override
                        public void run() {
                            onResultCallback.onSuccess(result);
                        }
                    });
                } catch (Exception e) {
                    onResultCallback.onError(e);
                }
            }
        });
    }

}
