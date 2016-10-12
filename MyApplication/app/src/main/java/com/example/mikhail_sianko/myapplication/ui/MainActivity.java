package com.example.mikhail_sianko.myapplication.ui;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mikhail_sianko.myapplication.HttpClient;
import com.example.mikhail_sianko.myapplication.R;

public class MainActivity extends AppCompatActivity implements Contract.View {

    private Contract.Presenter presenter;

    private TextView responseView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new MainPresenter(this);
        setContentView(R.layout.activity_main);

        responseView = (TextView) findViewById(R.id.responseView);
        progressBar = ((ProgressBar) findViewById(R.id.progressIndicator));

        presenter.onReady();
    }

    @Override
    public void showData(String data) {
        responseView.setText(data);
    }

    @Override
    public void showError(String message) {
        new AlertDialog.Builder(this).setMessage(message).create().show();
    }

    @Override
    public void showProgress(boolean isInProgress) {
        progressBar.setVisibility(isInProgress ? View.VISIBLE : View.GONE);
    }
}
