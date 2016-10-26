package com.example.mikhail_sianko.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mikhail_sianko.myapplication.R;
import com.example.mikhail_sianko.myapplication.adapter.ListViewAdapter;
import com.example.mikhail_sianko.myapplication.adapter.RecyclerViewAdapter;
import com.example.mikhail_sianko.myapplication.model.gson.TwitterSearchStatuses;

import java.util.ArrayList;
import java.util.List;


public class ListViewActivity extends AppCompatActivity {

    private static final String TAG = "ListViewActivity";

    private ListView listView;
    private List<TwitterSearchStatuses> statusesList = new ArrayList<>();
    private ListViewAdapter listViewAdapter;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_activity);

        startActivity(new Intent(this, ImagesActivity.class));
        findViewById(R.id.listContainer).setVisibility(View.GONE);
        findViewById(R.id.recyclerContainer).setVisibility(View.VISIBLE);

        for (int i = 0; i < 1000; i++) {
            TwitterSearchStatuses dummy = TwitterSearchStatuses.dummy("Text " + i, "User " + i);
            dummy.changeMe = i % 2 == 0;
            statusesList.add(dummy);
        }

//        bindListView();
        bindRecyclerView();
    }

    private void bindRecyclerView() {
        recyclerView = ((RecyclerView) findViewById(R.id.recyclerView));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAdapter = new RecyclerViewAdapter();
        recyclerViewAdapter.setData(statusesList);
        recyclerViewAdapter.setOnItemListener(new RecyclerViewAdapter.onItemListener() {
            @Override
            public void onClick(TwitterSearchStatuses item, int position) {
                Toast.makeText(ListViewActivity.this, item.getText(), Toast.LENGTH_SHORT).show();

                if (item.changeMe) {
                    recyclerViewAdapter.addItem(TwitterSearchStatuses.dummy("New", "New"), position);
                } else {
                    recyclerViewAdapter.removeItem(position);
                }
            }
        });
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    private void bindListView() {
        listView = ((ListView) findViewById(R.id.listView));

        listViewAdapter = new ListViewAdapter(this, 0, statusesList);
        listView.setAdapter(listViewAdapter);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                for (TwitterSearchStatuses statuses : statusesList) {
                    statuses.getTwitterUser().setName("Changed");
                }
                listViewAdapter.notifyDataSetChanged();
            }
        }, 5000);
    }

}
