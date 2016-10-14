package com.example.mikhail_sianko.myapplication.examples;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.mikhail_sianko.myapplication.R;

public class LayoutsActivity extends AppCompatActivity {

    public void onHamburgerIconClick(View view) {
        Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
    }

    public void onOneTimeButtonClick(View view) {
        Toast.makeText(this, "Clicked and gone", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layouts);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

}
