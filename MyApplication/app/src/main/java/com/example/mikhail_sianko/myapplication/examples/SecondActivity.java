package com.example.mikhail_sianko.myapplication.examples;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mikhail_sianko.myapplication.R;
import com.example.mikhail_sianko.myapplication.adapter.AbstractAdapter;
import com.example.mikhail_sianko.myapplication.http.HttpClientFactory;
import com.example.mikhail_sianko.myapplication.http.IHttpClient;
import com.example.mikhail_sianko.myapplication.model.House;
import com.example.mikhail_sianko.myapplication.utils.ContextHolder;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        final House house = new House.Builder()
                .setTitle("")
                .setDoors("")
                .setSize(0)
                .build();

        final List<House> houses = new ArrayList<>();
        houses.add(house);

        final AbstractAdapter<House> adapter = new AbstractAdapter<House>() {

            @Override
            public void onBind(final AbstractViewHolder holder, final House pHouse, final int position, final int viewType) {
                holder.<TextView>get(R.id.text1).setText(pHouse.getTitle());
            }

            @Override
            public House getItem(final int positoin) {
                return houses.get(positoin);
            }

            @Override
            public AbstractViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
                return new AbstractViewHolder(LayoutInflater.from(ContextHolder.get()).inflate(R.layout.view_bars, null), R.id.text1);
            }

            @Override
            public int getItemCount() {
                return houses.size();
            }
        };
    }

}
