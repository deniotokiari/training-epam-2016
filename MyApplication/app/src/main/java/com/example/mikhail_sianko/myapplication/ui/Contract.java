package com.example.mikhail_sianko.myapplication.ui;


public interface Contract {
    interface View {
        void showData(String data);
        void showError(String message);
        void showProgress(boolean isInProgress);
    }

    interface Presenter {
        void onReady();

    }
}
