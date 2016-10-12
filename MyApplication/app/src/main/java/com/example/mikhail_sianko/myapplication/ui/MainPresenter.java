package com.example.mikhail_sianko.myapplication.ui;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.mikhail_sianko.myapplication.data.api.ApiManager;
import com.training2016.backend.trainingApi.TrainingApi;
import com.training2016.backend.trainingApi.model.DataBean;

import java.io.IOException;

import static android.content.ContentValues.TAG;


public class MainPresenter implements Contract.Presenter {

    private Contract.View view;
    private Handler handler;

    public MainPresenter(@NonNull Contract.View view) {
        this.view = view;
        handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void onReady() {
        view.showProgress(true);
        loadData();
    }

    private void loadData() {

        new Thread() {
            @Override
            public void run() {

                try {
                    TrainingApi.GetStats call = ApiManager.get().trainingsApi().getStats();
                    DataBean bean = call.execute();
                    String response = bean.getData();
                    notifyResponse(response);
                } catch (IOException e) {
                    Log.e(TAG, "run: ", e);
                    notifyError(e);
                }
            }
        }.start();
    }

    private void notifyResponse(final String response) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                view.showProgress(false);
                view.showData(response);
            }
        });
    }

    private void notifyError(final Throwable e) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                view.showProgress(false);
                view.showError(e.getMessage());
            }
        });
    }
}
