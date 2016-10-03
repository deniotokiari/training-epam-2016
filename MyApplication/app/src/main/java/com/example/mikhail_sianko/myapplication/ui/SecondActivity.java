package com.example.mikhail_sianko.myapplication.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.mikhail_sianko.myapplication.R;


/**
 * Created by Alex Dzeshko on 30-Sep-16.
 */

public class SecondActivity extends AppCompatActivity {

    TextView textView;

    boolean state;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        onNewIntent(getIntent());
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.start(SecondActivity.this);
            }
        });

        findViewById(R.id.btn_fragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment();
            }
        });
    }

    private void addFragment() {
        state = !state;
        getSupportFragmentManager().beginTransaction().add(R.id.container, FirstFragment.newInstance(state)).addToBackStack("state").commit();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        textView.setText(getIntent().getStringExtra("value"));
    }

    public static void start(Context context, String value) {
        Intent intent = new Intent(context, SecondActivity.class);
        intent.putExtra("value", value);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        context.startActivity(intent);
    }
}
